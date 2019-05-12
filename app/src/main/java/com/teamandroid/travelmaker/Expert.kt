package com.teamandroid.travelmaker

data class Expert (
        var user_idx : Int,
        var user_id : String,
        var user_name : String,
        var user_age : String,
        var user_gender : String,
        var user_nick : String,
        var user_email : String,
        var user_style : Int,
        var user_img : String?,
        var user_expert : Int?,
        var expert_city1 : String?,
        var expert_city2 : String?,
        var expert_city3 : String?,
        var expert_rate : Double?,
        var user_budget : Int?,
        var expert_grade : Int?
)