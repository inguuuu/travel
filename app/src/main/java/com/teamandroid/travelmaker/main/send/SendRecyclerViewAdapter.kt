package com.teamandroid.travelmaker.main.send

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.R

class SendRecyclerViewAdapter(var items : ArrayList<SendBoard>) : RecyclerView.Adapter<SendRecyclerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SendRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_send_item, parent, false)

        return SendRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SendRecyclerViewHolder, position: Int) {
        holder.to.text = items[position].user_nick
        holder.contents.text = items[position].board_data.board_title

        if(items[position].board_data.board_status == 2 ){
            holder.confirm0.visibility = View.INVISIBLE
            holder.confirm1.visibility = View.VISIBLE
        }
        else if(items[position].board_data.board_status == 3){
            holder.confirm0.visibility = View.INVISIBLE
            holder.confirm2.visibility = View.VISIBLE
        }
        else if(items[position].board_data.board_status == 4){
            holder.confirm0.visibility = View.INVISIBLE
            holder.goPlan.visibility = View.VISIBLE
            holder.goPlan.isEnabled = true

        }
    }

    fun addItem(items : ArrayList<SendBoard>){
        this.items = items
        notifyDataSetChanged()
    }
}