package com.teamandroid.travelmaker.travelplanread

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.teamandroid.travelmaker.R
import kotlinx.android.synthetic.main.get_schdule_item.view.*

class GetScheduleAdapter(var scheduleItems : ArrayList<GetScheduleItem>, var plan : ArrayList<GetPlan>, context : Context) : RecyclerView.Adapter<GetScheduleViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetScheduleViewHolder {
        val layoutInflater  = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.get_schdule_item, parent, false)
        return GetScheduleViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: GetScheduleViewHolder, position: Int) {
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

                val intent = Intent(context, GetSpecificActivity::class.java)
                intent.putExtra("notFirstToSpecific", curDay)
                intent.putParcelableArrayListExtra("place",plan[position].place)
                intent.putParcelableArrayListExtra("trans",plan[position].trans)
                context.startActivity(intent)
            }
        })
    }

    override fun getItemCount(): Int {
        return  scheduleItems.size
    }
}