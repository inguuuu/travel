package com.teamandroid.travelmaker.detail

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import com.teamandroid.travelmaker.R

class MoreApplicationsRecyclerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val view : LinearLayout = itemView.findViewById(R.id.view)
    val no : TextView = itemView.findViewById(R.id.no)
    val title : TextView = itemView.findViewById(R.id.title)
    val comments : TextView = itemView.findViewById(R.id.numOfComment)
}