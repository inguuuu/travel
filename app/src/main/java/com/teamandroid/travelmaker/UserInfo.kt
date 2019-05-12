package com.teamandroid.travelmaker

data class UserInfo (
    var user_idx : Int,
    var naverUserInfo : NaverUserInfo,
    var user_expert : Int,
    var expert_city1 : String?,
    var expert_city2 : String?,
    var expert_city3 : String?,
    var expert_rate : Double?,
    var user_budget: Int,
    var expert_grade : Int?
)