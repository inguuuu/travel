package com.teamandroid.travelmaker.travelplanread

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.TravelMakerApplication
import kotlinx.android.synthetic.main.activity_get_plan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetPlanActivity : AppCompatActivity() {

    lateinit var map_tab : View
    lateinit var schedule_tab : View
    lateinit var map_btn : TextView
    lateinit var schedule_btn : TextView
    lateinit var scheduleFragment : GetScheduleTapFragment
    lateinit var mapFragment: ReturnMapTabFragment
    lateinit var fl_tab : FrameLayout
    lateinit var plan : ArrayList<GetPlan>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_plan)

        schedule_btn = findViewById(R.id.schedule_btn)
        map_btn = findViewById(R.id.map_btn)
        fl_tab = findViewById(R.id.tab_fl)

        requestPlanData()


//        val layoutInflater:LayoutInflater = LayoutInflater.from(applicationContext)

//        map_tab = layoutInflater.inflate(
//                R.layout.map_tab, // Custom view/ layout
//                fl_tab, // Root layout to attach the view
//                false // Attach with root layout or not
//        )
//        schedule_tab  = layoutInflater.inflate(
//                R.layout.schedule_tab,
//                fl_tab,
//                false
//        )

//        tran.add(R.id.tab_fl, tapFragment)  //replace의 매개변수는 (프래그먼트를 담을 영역 id, 프래그먼트 객체) 입니다.

        schedule_btn.setOnClickListener {
            changeView(0)
        }
        map_btn.setOnClickListener{
            changeView(1)
        }
        review_btn.setOnClickListener {
            val intent = Intent(application,GetReviewActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("ResourceAsColor")
    fun changeView (i : Int) {

        when(i){
            0 -> {
                setFrag(0)
                map_btn.setBackgroundResource(R.drawable.btn_map)
                schedule_btn.setBackgroundResource(R.drawable.btn_schedule)
                map_btn.setTextColor(Color.parseColor("#707070"))
                schedule_btn.setTextColor(Color.parseColor("#049DED"))
            }
            1 -> {
                setFrag(1)
                map_btn.setBackgroundResource(R.drawable.btn_schedule)
                schedule_btn.setBackgroundResource(R.drawable.btn_map)
                map_btn.setTextColor(Color.parseColor("#049DED"))
                schedule_btn.setTextColor(Color.parseColor("#707070"))
            }
        }
    }

    fun setFrag(n: Int) {    //프래그먼트를 교체하는 작업을 하는 메소드를 만들었습니다
        val fm = supportFragmentManager
        val tran = fm.beginTransaction()
        when (n) {
            0 -> {
                tran.replace(R.id.tab_fl, scheduleFragment)  //replace의 매개변수는 (프래그먼트를 담을 영역 id, 프래그먼트 객체) 입니다.
                tran.commit()
            }
            1 -> {
                tran.replace(R.id.tab_fl, mapFragment)  //replace의 매개변수는 (프래그먼트를 담을 영역 id, 프래그먼트 객체) 입니다.
                tran.commit()
            }
        }
    }



    fun requestPlanData(){
        val getPlan  = (applicationContext as TravelMakerApplication).getApplicationNetworkService().getPlan(
                (applicationContext as TravelMakerApplication).userToken,
                intent.getIntExtra("board_idx",0)
        )

        getPlan.enqueue(object : Callback<GetPlanData> {
            override fun onFailure(call: Call<GetPlanData>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<GetPlanData>?, response: Response<GetPlanData>?) {
                if(response!!.isSuccessful){
                    plan = response.body()!!.total_plan
                    scheduleFragment = GetScheduleTapFragment.newInstance(plan)
                    mapFragment = ReturnMapTabFragment()
                    val fm = supportFragmentManager
                    val tran = fm.beginTransaction()
                    tran.add(R.id.tab_fl, scheduleFragment)
                    tran.commit()
                }
            }

        })
    }


    fun getDay() : Int{
        return plan.size
    }
}
