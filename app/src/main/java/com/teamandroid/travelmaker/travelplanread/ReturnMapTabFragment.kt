package com.teamandroid.travelmaker.travelplanread
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.travelplanwrite.dataSets.Data
import com.teamandroid.travelmaker.travelplanwrite.dataSets.MapObject
import com.teamandroid.travelmaker.travelplanwrite.dataSets.TotalData

class ReturnMapTabFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.return_map, container, false)

        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val mOptions = MarkerOptions()
        mMap.clear() // 지도의 마커 다 제거
        mOptions.title("마커 좌표")

//        MapObject.latitude.size

        for(i in 0..(TotalData.totalData.size -1 )){
            var dataList : ArrayList<Data> = TotalData.totalData.get(i)!!
            for(tempdata in dataList){
                mOptions.position(LatLng(tempdata.latitude!!, tempdata.latitude!!))
            }
        }

        for(i in 0..MapObject.latitude.size - 1) {// DB에 저장된 값들을 뽑아서 마커로 표시함

            mOptions.position(LatLng(MapObject.latitude[i], MapObject.longitude[i]))
            googleMap.addMarker(mOptions)

        }
        if(MapObject.latitude.isEmpty()) {// 초기화면 설정// 검색 안하고 클릭시 나오는 시작점
            val start = LatLng(37.554785543432, 127.00477112084627)
            mMap!!.moveCamera(CameraUpdateFactory.newLatLng(start))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(start, 5f));///줌
        }else{ // 초기화면설정 //검색을하면 처음 검색한곳을 기준으로 보여줌
            var start = LatLng(MapObject.latitude[0], MapObject.longitude[0])// 입력 없이 처음 화면 위치
            mMap!!.moveCamera(CameraUpdateFactory.newLatLng(start))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(start, 5f));///줌
        }

    }
}