package com.teamandroid.travelmaker.post

import com.teamandroid.travelmaker.main.send.SendBoard

data class PostSendApplication (
    var message : String,
    var receive_board : ArrayList<SendBoard>
)