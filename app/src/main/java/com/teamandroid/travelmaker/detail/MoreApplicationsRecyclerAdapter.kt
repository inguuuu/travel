package com.teamandroid.travelmaker.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamandroid.travelmaker.Application
import com.teamandroid.travelmaker.Expert
import com.teamandroid.travelmaker.R

class MoreApplicationsRecyclerAdapter(var item : ArrayList<Application>) : RecyclerView.Adapter<MoreApplicationsRecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreApplicationsRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.apply_item, parent, false)

        return MoreApplicationsRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: MoreApplicationsRecyclerViewHolder, position: Int) {
        holder.no.text = (position+1).toString()
        holder.title.text = item[position].board_title
        holder.comments.text = item[position].comment_count.toString()
    }

    fun addItems(item : ArrayList<Application>){
        this.item = item
        notifyDataSetChanged()
    }
}