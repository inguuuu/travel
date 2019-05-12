package com.teamandroid.travelmaker.main.favorite

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.teamandroid.travelmaker.R

class PersonRecyclerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val name : TextView = itemView.findViewById(R.id.expert_name)
    val tendency : TextView = itemView.findViewById(R.id.expert_tendency)
    val city : TextView = itemView.findViewById(R.id.expert_city)
    val ratingBar : RatingBar = itemView.findViewById(R.id.expert_ratingBar)
    val ratingValue : TextView = itemView.findViewById(R.id.expert_ratingValue)
    val profile : ImageView = itemView.findViewById(R.id.expert_profile)
    val crown : ImageView = itemView.findViewById(R.id.expert_crown)
}