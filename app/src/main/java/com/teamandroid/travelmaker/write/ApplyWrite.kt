package com.teamandroid.travelmaker.write

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresPermission
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils.isEmpty
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_apply_write.*
import java.util.*
import kotlin.collections.ArrayList
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.teamandroid.travelmaker.*
import com.teamandroid.travelmaker.R.id.*
import com.teamandroid.travelmaker.post.PostWirteApplication
import kotlinx.android.synthetic.main.activity_apply_write.view.*
import kotlinx.android.synthetic.main.apply_write_item.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ApplyWrite : AppCompatActivity(), View.OnClickListener {

    lateinit var items : ArrayList<Empty>
    lateinit var adapter : ApplyWriteRecyclerViewAdapter
    lateinit var during : String
    lateinit var activity : ApplyWrite
    lateinit var startDate : Date
    lateinit var endDate : Date

    lateinit var _inn : ArrayList<Inn>
    lateinit var _out : ArrayList<Out>
    lateinit var _acc : ArrayList<Acc>

    var currentCoin = 0
    var payCoin = 100
    var residueCoin = 0

    var expert_idx = -1
    var expert_grade = -1
    var city = arrayOf("베이징","상하이","시안")
    var least_date = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_write)



        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.parseColor("#14DAC6")
        }

        _inn = ArrayList()
        _out = ArrayList()
        _acc = ArrayList()
        items = ArrayList()
        items.add(Empty())
        activity = this
        adapter = ApplyWriteRecyclerViewAdapter(items, this)

        expert_idx = intent.getIntExtra("expert_idx", -1)
        expert_grade = intent.getIntExtra("expert_grade", -1)


        btnDateFragment.setOnClickListener(this)
        plus_btn.setOnClickListener(this)
        btn_close.setOnClickListener(this)
        btn_ok.setOnClickListener(this)
        //spinner.prompt = "[도시]"

        val _adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, city)

        _adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = _adapter

        //spinner.setBackgroundColor(Color.parseColor("#14DAC6"))

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //val index = parent!!.selectedItemPosition
                (spinner.selectedView as TextView).setTextColor(Color.parseColor("#14DAC6"))
            }

        }

        currentCoin = (applicationContext as TravelMakerApplication).userInfo.user_budget

        calculateCoin()

        apply_rv.adapter = adapter
        apply_rv.layoutManager = LinearLayoutManager(this)

    }


    override fun onClick(v: View?) {

        if(v == btnDateFragment){
            val calendar = Calendar.getInstance()

            val datePickerDialog = DatePickerDialog(activity, object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    during = year.toString() + "-" + (month + 1).toString()  + "-" + dayOfMonth.toString()
                    startDate = Date(year,month+1,dayOfMonth)
                    val datePickerDialog = DatePickerDialog(activity, object : DatePickerDialog.OnDateSetListener{
                        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                            during += " ~ "+year.toString() + "-" + (month + 1).toString() + "-" + dayOfMonth.toString()

                            endDate = Date(year,month+1,dayOfMonth+1)

                            least_date = caculatedate()
                            if(least_date != 0){
                                total_date.text = least_date.toString() + "일"
                                btnDateFragment.text = during
                            }
                            else{
                                btnDateFragment.setText("")
                                total_date.text = "1일"
                            }
                            Log.d("day",least_date.toString())
                        }
                    },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)).show()
                }
            },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)).show()
        }

        else if(v == plus_btn){
            items.add(Empty())
            adapter.addItem(items)
        }

        else if(v == btn_close){
            onBackPressed()
        }
        else if(v == btn_ok){
            registApplication()
        }
    }

    fun caculatedate() : Int{
        val calendar = Calendar.getInstance()
        if(startDate.year != endDate.year){
            return 0
        }
        else if(endDate.month > startDate.month){
            calendar.set(Calendar.MONTH,startDate.date)
            val total_date = calendar.get(Calendar.DAY_OF_MONTH)
            val least_date = total_date - startDate.date + endDate.date

            if(least_date > 5){
                return 0
            }

            else
                return least_date
        }
        else if(endDate.date < startDate.date || endDate.date - startDate.date > 5){
            return 0
        }
        else{
            return endDate.date - startDate.date
        }
    }


    inner class Date(var year : Int, var month : Int, var date : Int)


    fun registApplication(){
        val board_title = write_title.text.toString()
        val board_city = (spinner.selectedView as TextView).text.toString()
        var board_arr_time = ""
        var board_dep_time = ""
        val board_days = least_date
        val board_content = write_etc.text.toString()

        var country_idx = intent.getIntExtra("country_idx",8)


        val stringTokenizer = StringTokenizer(btnDateFragment.text.toString(), " ~ ")

        if(stringTokenizer.hasMoreTokens()){
            board_arr_time = stringTokenizer.nextToken()
            board_dep_time = stringTokenizer.nextToken()
        }

        for(i in 0..(items.size-1)){
            val holder = apply_rv.findViewHolderForAdapterPosition(i) as ApplyWriteViewHolder
            if(holder.in_date.text.isEmpty()){
                return
            }

            if(holder.out_date.text.isEmpty()){
                return
            }
        }

        if(board_title.isEmpty() ||  board_arr_time.isEmpty() || board_dep_time.isEmpty())
            return

        for(i in 0..(items.size -1)){
            val holder = apply_rv.findViewHolderForAdapterPosition(i) as ApplyWriteViewHolder
            _inn.add(Inn(holder.in_place.text.toString(), holder.in_date.text.toString()))
            _out.add(Out(holder.out_place.text.toString(),holder.out_date.text.toString()))
            _acc.add(Acc(holder.acc_place.text.toString()))
        }

        val board_plan = ArrayList<BoardPlan>()
        board_plan.add(BoardPlan(_inn, _acc, _out))

        val writeApplication = WriteApplication(board_title, board_city, board_arr_time, board_dep_time, board_days, board_content,payCoin,
                country_idx,board_plan  ,null)

        Log.d("day",board_days.toString())

        if(expert_idx != -1){
            writeApplication.expert_idx = expert_idx
        }
        val postWirteApplication = (applicationContext as TravelMakerApplication).getApplicationNetworkService().postWriteApplication(
                (applicationContext as TravelMakerApplication).userToken,writeApplication)

        postWirteApplication.enqueue(object : Callback<PostWirteApplication>{
            override fun onFailure(call: Call<PostWirteApplication>?, t: Throwable?) {
                t!!.printStackTrace()
            }

            override fun onResponse(call: Call<PostWirteApplication>?, response: Response<PostWirteApplication>?) {
                if(response!!.isSuccessful){
                    finish()
                }else{
                    Log.d("error","error")
                }
            }

        })
    }

    fun calculateCoin(){

        if(expert_grade != -1){
            have_coin.text = currentCoin.toString() + "코인"
            payCoin = ((expert_grade +1)* 100)
            pay.text = payCoin.toString() + "코인"
            total_coin.text = pay.text
            residueCoin = currentCoin - payCoin

            residue_coin.text = residueCoin.toString() + "코인"
            total_coin.isClickable = false
        }
        else{
            have_coin.text = currentCoin.toString() + "코인"
            pay.text = payCoin.toString() + "코인"
            total_coin.text = pay.text

            residueCoin = currentCoin - payCoin

            residue_coin.text = residueCoin.toString() + "코인"
        }
    }

}
