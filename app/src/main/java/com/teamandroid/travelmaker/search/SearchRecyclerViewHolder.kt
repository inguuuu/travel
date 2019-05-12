package com.teamandroid.travelmaker.search

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.teamandroid.travelmaker.R

class SearchRecyclerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var countryName : TextView = itemView.findViewById(R.id.search_item_name)
    var container : LinearLayout = itemView.findViewById(R.id.search_item_box)
}