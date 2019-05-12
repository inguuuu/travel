package com.teamandroid.travelmaker.review

data class Board (
        var board_idx : Int,
        var country_idx : Int,
        var user_idx : Int,
        var expert_idx : Int,
        var board_title : String,
        var board_city : String,
        var board_dep_time : String,
        var board_arr_time : String,
        var board_content : String,
        var board_status : Int,
        var board_budget : Int,
        var board_days : Int,
        var board_coin : Int,
        var board_writetime : String
)