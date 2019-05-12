package com.teamandroid.travelmaker.get

import com.teamandroid.travelmaker.review.Board
import com.teamandroid.travelmaker.review.Comment
import com.teamandroid.travelmaker.review.Plan
import com.teamandroid.travelmaker.review.UserNickName

data class GetApplicationDetail(
        var message : String,
        var sender : ArrayList<UserNickName>,
        var board : ArrayList<Board>,
        var plan : ArrayList<Plan>,
        var comment : ArrayList<Comment>
)