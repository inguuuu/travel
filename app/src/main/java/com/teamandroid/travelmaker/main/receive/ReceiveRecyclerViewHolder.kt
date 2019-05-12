package com.teamandroid.travelmaker.main.receive

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_receive_item.view.*
import com.teamandroid.travelmaker.R

class ReceiveRecyclerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var from : TextView = itemView.findViewById(R.id.receive_from)
    var contents : TextView = itemView.findViewById(R.id.receive_contents)
    var createPlan : ImageView = itemView.findViewById(R.id.receive_createPlan)
}