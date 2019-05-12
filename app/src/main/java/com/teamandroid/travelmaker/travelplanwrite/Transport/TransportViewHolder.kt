package com.teamandroid.travelmaker.travelplanwrite.Transport

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.teamandroid.travelmaker.R


class TransportViewHolder (itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var transport_beginTime : TextView = itemView!!.findViewById(R.id.transport_beginTime) as TextView
    var transport_endTime : TextView = itemView!!.findViewById(R.id.transport_endTime) as TextView
    var transport_explain : TextView = itemView!!.findViewById(R.id.transport_explain) as TextView
    var transport_total : TextView = itemView!!.findViewById(R.id.transport_total) as TextView
    var transport_icon : ImageView = itemView!!.findViewById(R.id.transport_icon) as ImageView
}