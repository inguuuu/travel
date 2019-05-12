package com.teamandroid.travelmaker.travelplanread

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_specific.*
import com.bumptech.glide.RequestManager
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.TravelMakerApplication
import com.teamandroid.travelmaker.travelplanwrite.dataSets.*
import com.teamandroid.travelmaker.travelplanwrite.transdata.Place
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GetSpecificActivity : AppCompatActivity() {
    var curDay : Int = 0

    lateinit var specificItems : ArrayList<GetSpecificItem>
    lateinit var specificAdapter: GetSpecificAdapter
    lateinit var requestManager: RequestManager
    lateinit var place : ArrayList<GetPlace>
    lateinit var trans : ArrayList<GetTrans>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_get)

        val day : TextView = findViewById(R.id.day)
        lateinit var title_list : ArrayList<String>
        lateinit var hashTitle : HashMap<String, ArrayList<String>>
        hashTitle = HashMap()
        lateinit var tempArrayList : ArrayList<String>
        requestManager = Glide.with(this)

        //inputData
//        var title: String,
//        var latitude :Double?,
//        var longtitude : Double?,
//        var memo : Memo?,
//        var cost : Cost?,
//        var trans : Trans?
        /*
        var testDataArrayList_1 : ArrayList<Data> = ArrayList()
        var testDataArrayList_2 : ArrayList<Data> = ArrayList()

        var testMemo_1 = Memo("ㅁ", Uri.EMPTY)
        var testCost_1 = Cost("ads", "10000")
        var testTrans_1 = Trans("ad", "adsf", 1, "", "1000")


        var testMemo_2 = Memo("ㅁ", Uri.EMPTY)
        var testCost_2 = Cost("ads", "10000")
        var testTrans_2 = Trans("ad", "adsf", 1, "", "1000")

        var testMemo_4 = Memo("ㅁ", Uri.EMPTY)
        var testCost_4 = Cost("ads", "10000")
        var testTrans_4 = Trans("ad", "adsf", 1, "", "1000")

        var testData_1 = Data("자금성", 45.0, 45.0, testMemo_1, testCost_1, testTrans_1)
        var testData_2 = Data("자금성", 32.0, 47.0, testMemo_2, testCost_2, testTrans_2)
        var testData_4 = Data("asdfsd", 32.0, 47.0, testMemo_4, testCost_4, testTrans_4)

        testDataArrayList_1.add(testData_1)
        testDataArrayList_1.add(testData_2)
        testDataArrayList_1.add(testData_4)
        TotalData.totalData.put(1, testDataArrayList_1)

        var testMemo_3 = Memo("ㅁ", Uri.EMPTY)
        var testCost_3 = Cost("ads", "10000")
        var testTrans_3 = Trans("ad", "adsf", 1, "", "1000")
        var testData_3 = Data("자금성", 23.0, 54.0, testMemo_3, testCost_3, testTrans_3)

        testDataArrayList_2.add(testData_3)

        TotalData.totalData.put(2, testDataArrayList_2)*/


        if(intent.hasExtra("notFirstToSpecific")){
            curDay = intent.getIntExtra("notFirstToSpecific", 0)
            place = intent.getParcelableArrayListExtra("place")
            trans = intent.getParcelableArrayListExtra("trans")

//            DataSet(tempArrayList[0], tempArrayList[1])
            day.text = curDay.toString()
            // title_list = dataSet.getTitleList()
            // 1일차, 2일차 안에 각각의 장소 넣을 때 hashmap으로 따로 전달하기

            specificItems = ArrayList()

                for (i in 0..(trans.size - 1)) {
                    specificItems.add(GetSpecificItem(place[i].place_name))
                }

            Log.d("spec",place.size.toString())
            Log.d("spec",trans.size.toString())
            Log.d("spec",specificItems.size.toString())
            specificAdapter = GetSpecificAdapter(specificItems, GetPlan(place, trans), day.text.toString(), this,requestManager)
            specific_rv.layoutManager = LinearLayoutManager(this)
            specific_rv.adapter = specificAdapter
        }


        /*
        specificItems = ArrayList()
        //여기서 1일차 정보만 전달된다. 여기서 지도에서 지역을 받아 specificItems에 add해야한다

        if(TotalData.totalData.get(curDay) != null){
            for (s : Data in TotalData.totalData.get(curDay)!!) {
                specificItems.add(GetSpecificItem(s.title))
            }
        }

        specificAdapter = GetSpecificAdapter(specificItems, GetPlan(place, trans),day.text.toString(), this, requestManager)
        specific_rv.layoutManager = LinearLayoutManager(this)
        specific_rv.adapter = specificAdapter*/
    }

}
