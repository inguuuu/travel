//package com.yunjegal.travelplan
//
//import android.content.Intent
//import android.support.v7.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.widget.Button
//import android.widget.EditText
//import com.yunjegal.travelplan.Specific.SpecificActivity
//import com.yunjegal.travelplan.dataSets.Data
//import com.yunjegal.travelplan.dataSets.TotalData
//
//class MapActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_map)
//
//        val map_place : EditText = findViewById(R.id.map_place)
//        var curDay : Int = 0
//        var curPlace : String
//        var testBtn :Button  = findViewById(R.id.testBtn)
//        testBtn.setOnClickListener {
//            if (this.intent.hasExtra("firstToMap")) {
//                curPlace  = map_place.text.toString()
//                Log.d("확인", "여기는 First")
//                curDay = this.intent.getIntExtra("firstToMap", 0)
//                var intent = Intent(this, SpecificActivity::class.java)
//                intent.putExtra("frommap", curDay)
//                var tempData = Data(curPlace, null, null, null)
//                var tempArrayList: ArrayList<Data> = ArrayList()
//                tempArrayList.add(tempData)
//                TotalData.totalData.put(curDay, tempArrayList) //데이터베이스에서 hashmap 가져오기
//                startActivity(intent)
//                finish()
//            }
//            else if(this.intent.hasExtra("notFirstToMap")){
//                curPlace  = map_place.text.toString()
//                Log.d("확인", "여기는 notFirstToMap")
//                var intent = Intent(this, SpecificActivity::class.java)
//                curDay = this.intent.getStringExtra("notFirstToMap").toInt()
//                intent.putExtra("frommap", curDay)
//                var tempArrayList : ArrayList<Data> = TotalData.totalData.get(curDay)!!
//                tempArrayList.add(Data(curPlace, null, null, null))
//                TotalData.totalData.put(curDay, tempArrayList)
//                startActivity(intent)
//                finish()
//            }
//
//            // 1일차, 2일차 안에 각각의 장소 넣을 때 hashmap으로 따로 전달하기
//        }
//    }
//}
