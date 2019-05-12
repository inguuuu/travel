package com.teamandroid.travelmaker.main.home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.teamandroid.travelmaker.etc.InfiniteViewPager
import com.teamandroid.travelmaker.R

class HomeRecyclerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    var viewPager : InfiniteViewPager = itemView.findViewById(R.id.home_rc_viewPager)
    var countryName : TextView  = itemView.findViewById(R.id.home_rc_countryName)
    var footer : View = itemView.findViewById(R.id.home_rc_footer)
}