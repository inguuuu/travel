package com.teamandroid.travelmaker

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.bumptech.glide.Glide
import com.teamandroid.travelmaker.travelplanwrite.Specific.SpecificActivity
import com.teamandroid.travelmaker.travelplanwrite.dataSets.Data
import com.teamandroid.travelmaker.travelplanwrite.dataSets.TotalData
import com.teamandroid.travelmaker.travelplanwrite.dataSets.Trans
import kotlinx.android.synthetic.main.activity_memo.*
import kotlinx.android.synthetic.main.activity_transport.*
import kotlinx.android.synthetic.main.activity_transport.view.*
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream

class TransportActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var cspinner : Spinner
    var TRANSPORT_REQUEST : Int = 102

    lateinit var et_beginTime : EditText
    lateinit var et_endTime : EditText
    lateinit var et_explain : EditText
    lateinit var et_total : EditText
    var transport_icon : Int = 0

    lateinit var transport_beginTime : String
    lateinit var transport_endTime : String
    lateinit var transport_explain : String
    lateinit var transport_total : String

    var curDay :Int = 0
    var position : Int = 0

    override fun onClick(v: View?) {
        when(v){

            tab_car_btn->{
                clearSelected()
                tab_car_btn.isSelected=true
                transport_icon = 1
            }
            tab_airplane_btn->{
                clearSelected()
                tab_airplane_btn.isSelected=true
                transport_icon = 4
            }
            tab_walk_btn->{
                clearSelected()
                tab_walk_btn.isSelected=true
                transport_icon = 2
            }
            tab_train_btn->{
                clearSelected()
                tab_train_btn.isSelected=true
                transport_icon = 3
            }
            tab_bus_btn->{
                clearSelected()
                tab_bus_btn.isSelected=true
                transport_icon = 6
            }
            tab_bicycle_btn->{
                clearSelected()
                tab_bicycle_btn.isSelected=true
                transport_icon = 5
            }

            confirm_transport_btn->{
                et_beginTime = findViewById(R.id.transport_beginTime)
                et_endTime  = findViewById(R.id.transport_endTime)
                et_explain = findViewById(R.id.transport_explain)
                et_total = findViewById(R.id.transport_total)

                transport_beginTime = et_beginTime.text.toString()
                transport_endTime = et_endTime.text.toString()
                transport_explain = et_explain.text.toString()
                transport_total = et_total.text.toString()

                var arrayList: ArrayList<Data> = TotalData.totalData.get(curDay)!!
                var tempTrans = Trans(transport_beginTime, transport_endTime, transport_icon, transport_explain, transport_total)
                arrayList.get(position).trans = tempTrans
                TotalData.totalData.put(curDay, arrayList)
                Log.d("확인", transport_beginTime)
                Log.d("확인", transport_endTime)
                Log.d("확인", "트랜스!들어옴!!!!")
                intent = Intent(this, SpecificActivity::class.java)
                intent.putExtra("trans",curDay)
                startActivity(intent)
                finish()
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transport)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.parseColor("#14DAC6")
        }

        cspinner =findViewById(R.id.spinner_cost)
        //result = findViewById(R.id.result) as TextView
        val costarray = arrayOf("화폐단위", "KRW(한국)", "USD(미국)","CNY(중국)",
                "JPY(일본)", "GBP(영국)","CAD(캐나다)", "MXN(맥시코)", "EUR(유럽)")

        cspinner.adapter = ArrayAdapter<String>(this,R.layout.spinner_item_property,costarray)

        tab_bus_btn.setOnClickListener(this)
        tab_bicycle_btn.setOnClickListener(this)
        tab_car_btn.setOnClickListener(this)
        tab_airplane_btn.setOnClickListener(this)
        tab_train_btn.setOnClickListener(this)
        tab_walk_btn.setOnClickListener(this)
        confirm_transport_btn.setOnClickListener(this)

        if(intent.hasExtra("trans")){
            curDay = intent.getIntegerArrayListExtra("trans")[0]
            position = intent.getIntegerArrayListExtra("trans")[1]

        }
    }

    fun clearSelected(){
        tab_bus_btn.isSelected = false
        tab_bicycle_btn.isSelected = false
        tab_car_btn.isSelected = false
        tab_airplane_btn.isSelected = false
        tab_train_btn.isSelected = false
        tab_walk_btn.isSelected = false
    }
}