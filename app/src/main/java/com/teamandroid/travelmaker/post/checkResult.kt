package com.teamandroid.travelmaker.post

import com.teamandroid.travelmaker.UserInfo

data class CheckResult (
    var checkResult : ArrayList<UserInfo>,
    var token : String
)