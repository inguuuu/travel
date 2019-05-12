package com.teamandroid.travelmaker.travelplanwrite

import android.content.Intent
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.travelplanwrite.Specific.SpecificActivity
import com.teamandroid.travelmaker.travelplanwrite.dataSets.Data
import com.teamandroid.travelmaker.travelplanwrite.dataSets.MapObject
import com.teamandroid.travelmaker.travelplanwrite.dataSets.TotalData

import kotlinx.android.synthetic.main.activity_maps.*
import java.io.IOException

class MapsActivity : AppCompatActivity(), OnMapReadyCallback{

    private lateinit var mMap: GoogleMap
    var curDay : Int = 0
    var curPlace : String = ""
    var db_latitude : Double = 0.0
    var db_longitude : Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.parseColor("#14DAC6")
        }


        var edit : EditText = this.findViewById(R.id.editText)
        confirm_btn.setOnClickListener {
            if (this.intent.hasExtra("firstToMap")) {
                curPlace  = edit.text.toString()
                Log.d("확인", "여기는 First")
                curDay = this.intent.getIntExtra("firstToMap", 0)
                var intent = Intent(this, SpecificActivity::class.java)
                intent.putExtra("frommap", curDay)
                var tempData = Data(curPlace, db_latitude, db_longitude, null, null, null)
                var tempArrayList: ArrayList<Data> = ArrayList()
                tempArrayList.add(tempData)
                TotalData.totalData.put(curDay, tempArrayList) //데이터베이스에서 hashmap 가져오기
                startActivity(intent)
                finish()
            }
            else if(this.intent.hasExtra("notFirstToMap")){
                curPlace  = edit.text.toString()
                Log.d("확인", "여기는 notFirstToMap")
                var intent = Intent(this, SpecificActivity::class.java)
                curDay = this.intent.getStringExtra("notFirstToMap").toInt()
                intent.putExtra("frommap", curDay)
                var tempArrayList : ArrayList<Data> = TotalData.totalData.get(curDay)!!
                tempArrayList.add(Data(curPlace, db_latitude, db_longitude, null, null, null))
                TotalData.totalData.put(curDay, tempArrayList)
                startActivity(intent)
                finish()
            }

            // 1일차, 2일차 안에 각각의 장소 넣을 때 hashmap으로 따로 전달하기
        }
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //키보드  완료버튼 클릭시 검색
        val doneEditText = findViewById<View>(R.id.editText) as EditText
        editText.setInputType(InputType.TYPE_CLASS_TEXT)
        doneEditText.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                when(actionId){
                    EditorInfo.IME_ACTION_DONE->{
                        search()
                    }
                }
                return true;
            }

        })

    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val start = LatLng(37.554785543432, 127.00477112084627)// 초기화면 설정
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(start))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(start,4f));


    }
    fun search(){// 검색 함수
        val  locationSearch = findViewById(R.id.editText) as EditText

        val location = locationSearch.text.toString()
        var addressList: List<Address>? = null


        if (location != null || location != "") {
            val geocoder = Geocoder(this)
            try {
                addressList = geocoder.getFromLocationName(location, 1)
                Log.v("Maps", addressList!!.toString())
            } catch (e: IOException) {
                e.printStackTrace()
            }


            val splitStr = addressList!!.toString().split("]".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            Log.v("Maps", splitStr[0])
            Log.v("Maps", splitStr[1])

            val splitStr2 = splitStr!![1].toString().split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (i in 0..splitStr2.size - 1)
                Log.v("Maps", splitStr2[i])
            val latitude = splitStr2[10].split("=".toRegex())[1]// 위도
            val longitude = splitStr2[12].split("=".toRegex())[1]// 경도
            val feature = splitStr[1]// 위치명


            Log.v("Maps", latitude)
            Log.v("Maps", longitude)

            val point = LatLng(latitude.toDouble(), longitude.toDouble())
            val address = addressList!![0]
            val latLng = LatLng(address.getLatitude(), address.getLongitude())

            val mOptions2 = MarkerOptions()
            mOptions2.title("search result")
            mOptions2.snippet(feature)
            mOptions2.position(point)

            db_latitude = latitude.toDouble()// 검색시 위도 내부 MapObject에 저장
            db_longitude = longitude.toDouble()// 검색시 경도 내부 MapOject에 저장
            MapObject.latitude.add(db_latitude)// 검색시 위도 내부 MapObject에 저장
            MapObject.longitude.add(db_longitude)// 검색시 경도 내부 MapOject에 저장

            mMap.clear() // 다른 마커들 지우고 새로운 것만 지도에 표시하기 위해서 사용
            mMap.addMarker(mOptions2)
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 8f));
        }
    }
}
