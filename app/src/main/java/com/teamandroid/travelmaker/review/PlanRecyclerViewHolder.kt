package com.teamandroid.travelmaker.review

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.teamandroid.travelmaker.R

class PlanRecyclerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var in_title : TextView = itemView.findViewById(R.id.apply_in_title)
    var in_day : TextView = itemView.findViewById(R.id.apply_in_day)
    var hotel : TextView = itemView.findViewById(R.id.apply_hotel)
    var out_title : TextView = itemView.findViewById(R.id.apply_out_title)
    var out_day : TextView = itemView.findViewById(R.id.apply_out_day)
}