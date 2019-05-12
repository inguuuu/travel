package com.teamandroid.travelmaker.write


import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.collections.ArrayList
import com.teamandroid.travelmaker.R
import java.util.*

class ApplyWriteRecyclerViewAdapter(var items : ArrayList<Empty>, var activity: ApplyWrite)  : RecyclerView.Adapter<ApplyWriteViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplyWriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.apply_write_item, parent, false)
        return ApplyWriteViewHolder(view,activity)
    }

    override fun onBindViewHolder(holder: ApplyWriteViewHolder, position: Int) {
        return
    }

    override fun getItemCount(): Int = items.size

    fun addItem(items: ArrayList<Empty>){
        this.items = items
        notifyDataSetChanged()
    }

}