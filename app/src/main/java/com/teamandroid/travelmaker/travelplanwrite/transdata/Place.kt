package com.teamandroid.travelmaker.travelplanwrite.transdata

import android.net.Uri

data class Place (
        var place_name : String,
        var place_comment : String,
        var place_latitude : Double,
        var place_longitude : Double,
        var place_budget : Int,
        var place_budgcomment : String,
        var image : Int
)