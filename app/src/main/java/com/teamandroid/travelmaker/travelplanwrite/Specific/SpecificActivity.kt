package com.teamandroid.travelmaker.travelplanwrite.Specific

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.teamandroid.travelmaker.Acc
import com.teamandroid.travelmaker.Inn
import com.teamandroid.travelmaker.Out
import com.teamandroid.travelmaker.travelplanwrite.MapsActivity

import kotlinx.android.synthetic.main.activity_specific.*

import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.travelplanwrite.dataSets.Data
import com.teamandroid.travelmaker.travelplanwrite.dataSets.TotalData
import com.teamandroid.travelmaker.write.ApplyWriteViewHolder
import kotlinx.android.synthetic.main.activity_apply_write.*


class SpecificActivity : AppCompatActivity() {
    var curDay : Int = 0

    lateinit var specificItems : ArrayList<SpecificItem>
    lateinit var specificAdapter: SpecificAdapter
    lateinit var requestManager: RequestManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific)

        val day : TextView = findViewById(R.id.day)
        lateinit var title_list : ArrayList<String>
        lateinit var hashTitle : HashMap<String, ArrayList<String>>
        hashTitle = HashMap()
        lateinit var tempArrayList : ArrayList<String>

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.statusbarWhite, null)
        }
//        lateinit var dataSet : DataSet
        requestManager = Glide.with(this)

        if (this.intent.hasExtra("frommap")) {

            curDay = intent.getIntExtra("frommap", 0)
//            DataSet(tempArrayList[0], tempArrayList[1])
            day.text = curDay.toString()

            // title_list = dataSet.getTitleList()
            // 1일차, 2일차 안에 각각의 장소 넣을 때 hashmap으로 따로 전달하기

            specificItems = ArrayList()
            //여기서 1일차 정보만 전달된다. 여기서 지도에서 지역을 받아 specificItems에 add해야한다
            if(TotalData.totalData.get(curDay) != null){
                for (s : Data in TotalData.totalData.get(curDay)!!) {
                    specificItems.add(SpecificItem(s.title))
                }
            } else {

            }

            specificAdapter = SpecificAdapter(specificItems, day.text.toString(), this,requestManager)
            specific_rv.layoutManager = LinearLayoutManager(this)
            specific_rv.adapter = specificAdapter
        }

        else if(this.intent.hasExtra("notFirstToSpecific")){
            curDay = intent.getIntExtra("notFirstToSpecific", 0)
//            DataSet(tempArrayList[0], tempArrayList[1])
            day.text = curDay.toString()
            // title_list = dataSet.getTitleList()
            // 1일차, 2일차 안에 각각의 장소 넣을 때 hashmap으로 따로 전달하기

            specificItems = ArrayList()
            //여기서 1일차 정보만 전달된다. 여기서 지도에서 지역을 받아 specificItems에 add해야한다
            if(TotalData.totalData.get(curDay) != null){
                for (s : Data in TotalData.totalData.get(curDay)!!) {
                    specificItems.add(SpecificItem(s.title))
                }
            } else {

            }

            specificAdapter = SpecificAdapter(specificItems, day.text.toString(), this,requestManager)
            specific_rv.layoutManager = LinearLayoutManager(this)
            specific_rv.adapter = specificAdapter
        }

        else if(this.intent.hasExtra("memo")){

            //여기서 1일차 정보만 전달된다. 여기서 지도에서 지역을 받아 specificItems에 add해야한다
            curDay = intent.getIntExtra("memo", 0)
            day.text = curDay.toString()
            specificItems = ArrayList()
            if(TotalData.totalData.get(curDay) != null){
                for (s : Data in TotalData.totalData.get(curDay)!!) {
                    specificItems.add(SpecificItem(s.title))
                }
            } else {

            }
            specificAdapter = SpecificAdapter(specificItems, day.text.toString(), this,requestManager)
            specific_rv.layoutManager = LinearLayoutManager(this)
            specific_rv.adapter = specificAdapter
            specificAdapter.notifyDataSetChanged()
        }

        else if(this.intent.hasExtra("cost")){

            //여기서 1일차 정보만 전달된다. 여기서 지도에서 지역을 받아 specificItems에 add해야한다
            curDay = intent.getIntExtra("cost", 0)
            day.text = curDay.toString()
            specificItems = ArrayList()
            if(TotalData.totalData.get(curDay) != null){
                for (s : Data in TotalData.totalData.get(curDay)!!) {
                    specificItems.add(SpecificItem(s.title))
                }
            } else {

            }
            specificAdapter = SpecificAdapter(specificItems, day.text.toString(), this,requestManager)
            specific_rv.layoutManager = LinearLayoutManager(this)
            specific_rv.adapter = specificAdapter
            specificAdapter.notifyDataSetChanged()

        }

        else if(this.intent.hasExtra("trans")){

            //여기서 1일차 정보만 전달된다. 여기서 지도에서 지역을 받아 specificItems에 add해야한다
            curDay = intent.getIntExtra("trans", 0)
            day.text = curDay.toString()
            specificItems = ArrayList()
            if(TotalData.totalData.get(curDay) != null){
                for (s : Data in TotalData.totalData.get(curDay)!!) {
                    specificItems.add(SpecificItem(s.title))
                }
            } else {

            }
            specificAdapter = SpecificAdapter(specificItems, day.text.toString(), this,requestManager)
            specific_rv.layoutManager = LinearLayoutManager(this)
            specific_rv.adapter = specificAdapter
            specificAdapter.notifyDataSetChanged()

        }


        _plus_btn.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            intent.putExtra("notFirstToMap", day.text)
            startActivity(intent)
            finish()

        }

        specificItems = ArrayList()
        //여기서 1일차 정보만 전달된다. 여기서 지도에서 지역을 받아 specificItems에 add해야한다
        if(TotalData.totalData.get(curDay) != null){
            for (s : Data in TotalData.totalData.get(curDay)!!) {
                specificItems.add(SpecificItem(s.title))
            }
        } else {

        }

        specificAdapter = SpecificAdapter(specificItems, day.text.toString(), this,requestManager)
        specific_rv.layoutManager = LinearLayoutManager(this)
        specific_rv.adapter = specificAdapter

        save_btn.setOnClickListener {
            finish()
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//       // super.onActivityResult(requestCode, resultCode, data)
//
//        if(resultCode == RESULT_OK && requestCode == 100){
//
//            specificItems = ArrayList()
//            //여기서 1일차 정보만 전달된다. 여기서 지도에서 지역을 받아 specificItems에 add해야한다
//            if(TotalData.totalData.get(curDay) != null){
//                for (s : Data in TotalData.totalData.get(curDay)!!) {
//                    specificItems.add(SpecificItem(s.title))
//                }
//            }
//        }
//    }
}
