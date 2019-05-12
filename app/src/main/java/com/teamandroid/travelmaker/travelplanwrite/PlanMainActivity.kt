package com.teamandroid.travelmaker

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.TextView
import com.google.gson.Gson
import com.teamandroid.travelmaker.get.GetApplicationDetail
import com.teamandroid.travelmaker.travelplanwrite.Schedule.MapTabFragment
import com.teamandroid.travelmaker.travelplanwrite.Schedule.ScheduleTapFragment
import com.teamandroid.travelmaker.travelplanwrite.dataSets.TotalData
import com.teamandroid.travelmaker.travelplanwrite.transdata.*
import kotlinx.android.synthetic.main.activity_plan_main.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File


class PlanMainActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var map_tab : View
    lateinit var schedule_tab : View
    lateinit var map_btn : TextView
    lateinit var schedule_btn : TextView
    lateinit var scheduleFragment : ScheduleTapFragment
    lateinit var mapFragment: MapTabFragment
    lateinit var fl_tab : FrameLayout
    lateinit var place_img : ArrayList<File>
    lateinit var plan : ArrayList<Plan>
    var board_idx: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_main)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.statusbarWhite, null)
        }

        schedule_btn = findViewById(R.id.schedule_btn)
        map_btn = findViewById(R.id.map_btn)
        fl_tab = findViewById(R.id.tab_fl)
        send_btn.setOnClickListener(this)
        board_idx = intent.getIntExtra("board_idx",-1)

        requestApplyData(board_idx)


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

    fun requestApplyData(board_idx : Int){
        val getApplicationDetail = (applicationContext as TravelMakerApplication).getApplicationNetworkService().getdetailApplication(board_idx)

        getApplicationDetail.enqueue(object : Callback<GetApplicationDetail> {
            override fun onFailure(call: Call<GetApplicationDetail>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<GetApplicationDetail>?, response: Response<GetApplicationDetail>?) {
                if(response!!.isSuccessful){
                    val board = response.body()!!.board

                    plan_title.setText("["+board[0].board_city +"] "+board[0].board_title)

                    btnDateFragment.text = board[0].board_dep_time + " ~ " + board[0].board_arr_time


                    scheduleFragment = ScheduleTapFragment.newInstance(board[0])
                    mapFragment = MapTabFragment()
                    val fm = supportFragmentManager
                    val tran = fm.beginTransaction()
                    tran.add(R.id.tab_fl, scheduleFragment)
                    tran.commit()

                    schedule_btn.setOnClickListener {
                        changeView(0)
                    }
                    map_btn.setOnClickListener{
                        changeView(1)
                    }
                }
            }

        })
    }

    override fun onClick(v: View?) {
        if(v == send_btn){
            place_img = ArrayList()
            plan = ArrayList()

            for (date : Int in 1..TotalData.totalData.size){
                var place_list : ArrayList<Place> = ArrayList()
                var trans_list : ArrayList<Trans> = ArrayList()
                for (data in TotalData.totalData.get(date)!!){
                    var place_item  = Place(data.title, data.memo!!.memo_title!!, data.latitude!!, data.longtitude!!, data.cost!!.cost_budget.toInt(), data.cost!!.cost_comment,1)
                    var trans_item = Trans(data.trans!!.trans_img.toString(), data.trans!!.trans_cost, data.trans!!.begin_time, data.trans!!.end_time, data.trans!!.trans_memo)
                    place_list.add(place_item)
                    trans_list.add(trans_item)
                    place_img.add(File(data.memo!!.memo_img.toString()))
                }
                plan.add(Plan(place_list, trans_list))
            }

            var gson = Gson()
            val json = gson.toJson(plan)

            val json1 = RequestBody.create(MediaType.parse("text/plain"),json)

            val boardIdx = RequestBody.create(MediaType.parse("text/plain"),board_idx.toString())

            val baos = ByteArrayOutputStream()

            val photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray())

            val image = ArrayList<MultipartBody.Part>()


            for(i in 0..(place_img.size -1)){
                image.add(MultipartBody.Part.createFormData("place_img", place_img[i].name, photoBody))
            }

            val postPlan = (applicationContext as TravelMakerApplication).getApplicationNetworkService().postPlan(
                    (applicationContext as TravelMakerApplication).userToken,
                    boardIdx,json1,image)

            postPlan.enqueue(object : Callback<TmPostResponse>{
                override fun onFailure(call: Call<TmPostResponse>?, t: Throwable?) {

                }

                override fun onResponse(call: Call<TmPostResponse>?, response: Response<TmPostResponse>?) {
                    if(response!!.isSuccessful){
                        finish()
                    }
                }

            })
        }
    }
}
