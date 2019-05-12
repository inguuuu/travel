package com.teamandroid.travelmaker.main.favorite

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.teamandroid.travelmaker.R


class CountryRecyclerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    var parent : RelativeLayout = itemView.findViewById(R.id.rc_vp_parent)
    var countryImage : ImageView = itemView.findViewById(R.id.rc_vp_countryImage)
    var countryName : TextView = itemView.findViewById(R.id.rc_vp_countryName)
}