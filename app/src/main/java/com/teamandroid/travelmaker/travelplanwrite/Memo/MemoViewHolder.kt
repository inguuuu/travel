package com.teamandroid.travelmaker.travelplanwrite.Memo

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.teamandroid.travelmaker.R

class MemoViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var memo_img : ImageView = itemView!!.findViewById(R.id.memo_img) as ImageView
    var memo_explain : TextView = itemView!!.findViewById(R.id.memo_explain) as TextView

}