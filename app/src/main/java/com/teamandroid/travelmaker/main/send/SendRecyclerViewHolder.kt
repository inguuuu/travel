package com.teamandroid.travelmaker.main.send

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_receive_item.view.*
import com.teamandroid.travelmaker.R
import org.w3c.dom.Text

class SendRecyclerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

    var to : TextView = itemView.findViewById(R.id.send_to)
    var contents : TextView = itemView.findViewById(R.id.send_contents)
    var confirm0 : TextView = itemView.findViewById(R.id.send_confirm0)
    var confirm1 : TextView = itemView.findViewById(R.id.send_confirm1)
    var confirm2 : TextView = itemView.findViewById(R.id.send_confirm2)
    var goPlan : LinearLayout = itemView.findViewById(R.id.send_goPlan)

    init {
        goPlan.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
    }
}