package com.teamandroid.travelmaker.post

import com.teamandroid.travelmaker.main.receive.ReceiveBoard

data class PostReceiveApplication (
    var message : String,
    var receive_board : ArrayList<ReceiveBoard>
)