package com.teamandroid.travelmaker.review

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamandroid.travelmaker.R

class PlanRecyclerAdapter(var items : ArrayList<Plan>) : RecyclerView.Adapter<PlanRecyclerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.apply_item_inside_item, parent, false)
        return PlanRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PlanRecyclerViewHolder, position: Int) {
        holder.in_title.text  = items[position].plan_in
        holder.in_day.text = items[position].plan_in_date
        holder.hotel.text = items[position].plan_acc_name
        holder.out_title.text = items[position].plan_out
        holder.out_day.text = items[position].plan_out_date
    }
}