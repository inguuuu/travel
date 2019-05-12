package com.teamandroid.travelmaker.travelplanwrite.Cost

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.teamandroid.travelmaker.R

class CostAdapter (var costItems : ArrayList<CostItem>) : RecyclerView.Adapter<CostViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.specific_cost_item, parent, false)
        return CostViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return costItems.size
    }

    override fun onBindViewHolder(holder: CostViewHolder, position: Int) {
        holder.cost_total.text = costItems[position].cost_total
        holder.cost_budget.text = costItems[position].cost_budget
    }
}