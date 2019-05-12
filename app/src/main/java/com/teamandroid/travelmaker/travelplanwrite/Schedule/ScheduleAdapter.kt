package com.teamandroid.travelmaker.travelplanwrite.Schedule

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.schedule_item.view.*
import android.content.Intent
import android.content.Context
import android.util.Log
import android.widget.TextView
import com.teamandroid.travelmaker.travelplanwrite.MapsActivity
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.travelplanwrite.Specific.SpecificActivity
import com.teamandroid.travelmaker.travelplanwrite.dataSets.TotalData


class ScheduleAdapter(var scheduleItems : ArrayList<ScheduleItem>, context : Context) : RecyclerView.Adapter<ScheduleViewHolder>() {

    var context = context
    var curDay : Int = 0
    //아이템 클릭시 실행 함수
    private var itemClick: ItemClick? = null

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    //아이템 클릭시 실행 함수 등록 함수
    fun setItemClick(itemClick: ItemClick) {
        this.itemClick = itemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.schedule_item, parent, false)
        return ScheduleViewHolder(cellForRow)
}

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.itemView.day.text = scheduleItems[position].day.toString()
        holder.itemView.write.text = scheduleItems[position].schedule

        holder.itemView.setOnClickListener(View.OnClickListener {
            v ->
            if (itemClick != null) {
                itemClick!!.onClick(v, position)
            }
        })

        setItemClick(object : ItemClick {
            override fun onClick(view: View, position: Int) {
                var day: TextView = view.findViewById(R.id.day)
                curDay = day.text.toString().toInt()

                if(TotalData.totalData.get(curDay) ==  null){
                    val intent = Intent(context, MapsActivity::class.java)
                    intent.putExtra("firstToMap", curDay)
                    context.startActivity(intent)

                } else{
                    val intent = Intent(context, SpecificActivity::class.java)
                    intent.putExtra("notFirstToSpecific", curDay)
                    context.startActivity(intent)

                }
            }
        })
    }

    override fun getItemCount(): Int {
        Log.d("holder",scheduleItems.size.toString())
        return  scheduleItems.size
    }
}