package com.teamandroid.travelmaker.travelplanwrite.Transport


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamandroid.travelmaker.R


class TransportAdapter (var transportItems : ArrayList<TransportItem>) : RecyclerView.Adapter<TransportViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransportViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.specific_cost_item, parent, false)
        return TransportViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return transportItems.size
    }

    override fun onBindViewHolder(holder: TransportViewHolder, position: Int) {
        holder.transport_beginTime.text = transportItems[position].transport_beginTime
        holder.transport_endTime.text = transportItems[position].transport_endTime
        holder.transport_explain.text = transportItems[position].transport_explain
        holder.transport_total.text = transportItems[position].transport_total
        holder.transport_icon.setImageResource(transportItems[position].transport_icon)


    }
}