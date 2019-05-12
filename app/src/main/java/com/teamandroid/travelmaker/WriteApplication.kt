package com.teamandroid.travelmaker

data class WriteApplication (
        var board_title : String,
        var board_city : String,
        var board_arr_time : String,
        var board_dep_time : String,
        var board_days : Int,
        var board_content : String?,
        var board_coin : Int?,
        var country_idx : Int,
        var board_plan : ArrayList<BoardPlan>,
        var expert_idx : Int?
)