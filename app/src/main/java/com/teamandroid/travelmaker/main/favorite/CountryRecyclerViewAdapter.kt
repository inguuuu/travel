package com.teamandroid.travelmaker.main.favorite

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.main.Country

class CountryRecyclerViewAdapter(var countries : ArrayList<Country>) : RecyclerView.Adapter<CountryRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_favorite_country_item, parent, false)

        return CountryRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: CountryRecyclerViewHolder, position: Int) {
        holder.countryImage.setImageBitmap(countries[position].thumbnailbitmap)
        holder.parent.tag = countries[position].countryData.country_idx
        holder.countryName.text = countries[position].countryData.country_name
    }
}