package com.teamandroid.travelmaker.main.receive

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.PlanMainActivity
import com.teamandroid.travelmaker.R

class ReceiveRecyclerViewAdapter(var items : ArrayList<ReceiveBoard>) : RecyclerView.Adapter<ReceiveRecyclerViewHolder>(){

    lateinit var mContext : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiveRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_receive_item, parent, false)
        mContext = parent.context
        return ReceiveRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ReceiveRecyclerViewHolder, position: Int) {
        holder.from.text = items[position].user_nick
        holder.contents.text = items[position].board_data.board_title

        if(items[position].board_data.board_status == 2 || items[position].board_data.board_status == 4 ){
            holder.createPlan.visibility = View.VISIBLE
            holder.createPlan.isEnabled = true
            holder.createPlan.setOnClickListener{
                val intent = Intent(mContext,PlanMainActivity::class.java)
                intent.putExtra("",items[position].board_data.board_idx)
                mContext.startActivity(intent)
            }
        }
    }

    fun addItem(items : ArrayList<ReceiveBoard>){
        this.items = items
        notifyDataSetChanged()
    }
}