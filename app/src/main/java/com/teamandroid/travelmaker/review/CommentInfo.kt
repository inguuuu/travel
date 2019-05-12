package com.teamandroid.travelmaker.review

data class CommentInfo (
    var comment_idx : Int,
    var user_idx : Int,
    var comment_content : String,
    var comment_writetime : String,
    var board_idx : Int
)