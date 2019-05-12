package com.teamandroid.travelmaker.travelplanread

data class GetTransportItem (
        var transport_beginTime : String,
        var transport_endTime : String,
        var transport_icon : Int,
        var transport_explain : String,
        var transport_total : String
)