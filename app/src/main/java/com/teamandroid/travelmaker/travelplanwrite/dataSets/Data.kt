package com.teamandroid.travelmaker.travelplanwrite.dataSets

data class Data (
        var title: String,
        var latitude :Double?,
        var longtitude : Double?,
        var memo : Memo?,
        var cost : Cost?,
        var trans : Trans?
)