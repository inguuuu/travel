package com.teamandroid.travelmaker.travelplanread

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.teamandroid.travelmaker.R

class GetSpecificViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var specific_title : TextView = itemView!!.findViewById(R.id.specific_title) as TextView

}