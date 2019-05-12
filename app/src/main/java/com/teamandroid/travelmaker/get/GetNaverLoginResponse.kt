package com.teamandroid.travelmaker.get

import com.teamandroid.travelmaker.etc.UserProfile

data class GetNaverLoginResponse (
    var resultcode : String,
    var message : String,
    var response : UserProfile
)