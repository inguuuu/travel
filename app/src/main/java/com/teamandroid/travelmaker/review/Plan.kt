package com.teamandroid.travelmaker.review

data class Plan (
        var plan_idx : Int,
        var country_idx : Int,
        var plan_count : Int,
        var plan_in : String,
        var plan_in_date : String,
        var plan_acc_name : String,
        var plan_out : String,
        var plan_out_date : String,
        var board_idx : Int
)