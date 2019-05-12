//package com.yunjegal.travelplan
//
//import android.hardware.camera2.TotalCaptureResult
//import java.util.ArrayList
//import java.util.HashMap
//import android.os.Parcel
//import android.os.Parcelable.Creator
//import android.os.Parcelable
//import com.yunjegal.travelplan.dataSets.Data
//import com.yunjegal.travelplan.dataSets.TotalData
//
//
//class DataSet {
//    internal var hashTitleMap: HashMap<Int, ArrayList<Data>> = TotalData.totalData
//    internal var day : String = ""
//
//    constructor(s: String, title: String) {
//        day = s
////        dataInit()
//        if (hashTitleMap.containsKey(s.toInt())) {
//            var temp = ArrayList<Data>()
//            var tempData = Data(title, null, null, null)
//            var tempArrayList : ArrayList<Data> = ArrayList()
//            tempArrayList.add(tempData)
//            hashTitleMap.put(s.toInt(), tempArrayList) !! //데이터베이스에서 hashmap 가져오기
//
//        }
////        else {
////            val temp = ArrayList<String>()
////            temp.add(title)
////            hashTitleMap[s] = temp
////        }
//    }
//    constructor() {
//
//    }
//    constructor(s : String){
//        day = s
//    }
//
////    fun getTitleList() : ArrayList<String>{
////        return hashTitleMap.get(day)!!
////    }
////    fun dataInit(){
////        hashTitleMap.put("1", arrayListOf("자금성", "베이징"))
////        hashTitleMap.put("2", arrayListOf("서울", "부산", "대구"))
////    }
//}