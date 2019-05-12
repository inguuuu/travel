package com.teamandroid.travelmaker.travelplanwrite.Cost

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.teamandroid.travelmaker.R

class CostViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var cost_total : TextView = itemView!!.findViewById(R.id.cost_comment) as TextView
    var cost_budget : TextView = itemView!!.findViewById(R.id.cost_budget) as TextView
}