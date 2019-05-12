package com.teamandroid.travelmaker.review

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.teamandroid.travelmaker.R

class CommentRecyclerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    var nickName : TextView
    var contents : TextView
    var btn_expert : TextView
    init {
        nickName = itemView.findViewById(R.id.comment_nickname)
        contents = itemView.findViewById(R.id.comment_contents)
        btn_expert = itemView.findViewById(R.id.btn_expert)
    }

}