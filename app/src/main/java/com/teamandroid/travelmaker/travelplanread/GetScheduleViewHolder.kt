package com.teamandroid.travelmaker.travelplanread

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.teamandroid.travelmaker.R

class GetScheduleViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var day : TextView = itemView!!.findViewById(R.id.day) as TextView
    var write : TextView = itemView!!.findViewById(R.id.write) as TextView
}