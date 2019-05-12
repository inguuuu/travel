package com.teamandroid.travelmaker.travelplanwrite.Specific

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.specific_cost_item.view.*
import kotlinx.android.synthetic.main.specific_memo_item.view.*
import kotlinx.android.synthetic.main.specific_place_item.view.*
import kotlinx.android.synthetic.main.specific_transport_item.view.*
import android.app.Activity
import android.net.Uri

import com.bumptech.glide.RequestManager
import com.teamandroid.travelmaker.CostActivity
import com.teamandroid.travelmaker.MemoActivity
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.TransportActivity
import com.teamandroid.travelmaker.travelplanwrite.Cost.CostItem
import com.teamandroid.travelmaker.travelplanwrite.Memo.MemoItem
import com.teamandroid.travelmaker.travelplanwrite.Transport.TransportItem
import com.teamandroid.travelmaker.travelplanwrite.dataSets.Data
import com.teamandroid.travelmaker.travelplanwrite.dataSets.TotalData


//import com.yunjegal.travelplan.Specific.SpecificAdapter.CallbackInterface

class SpecificAdapter(var specificItems : ArrayList<SpecificItem>, day : String, c : Context, var requestManager: RequestManager) : RecyclerView.Adapter<SpecificViewHolder>() {
    lateinit var place_item : View
    lateinit var cur_view : ViewGroup
    var context : Context = c
    var curDay : Int = day.toInt()

    lateinit var place_parent : ViewGroup
    lateinit var memoItems : ArrayList<MemoItem>
    lateinit var costItems : ArrayList<CostItem>
    lateinit var transportItems : ArrayList<TransportItem>
    lateinit var memo_view : View
    lateinit var cost_view : View
    lateinit var trans_view : View
    lateinit var memo_layout : LinearLayout
    lateinit var cost_layout : LinearLayout
    lateinit var trans_layout : LinearLayout
    private var memoAdapter: RecyclerView.Adapter<*>? = null
    private var costAdapter: RecyclerView.Adapter<*>? = null
    private var transportAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    var MEMO_REQUEST : Int = 100
    var COST_REQUEST : Int = 101
    var TRANSPORT_REQUEST : Int = 102


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecificViewHolder {
        val view : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.specific_place_item, parent, false)
        cur_view = view as ViewGroup
        return SpecificViewHolder(view)

//        memo_view = LayoutInflater.from(context).inflate(R.layout.specific_memo_item, parent, false)
//        cost_view = LayoutInflater.from(context).inflate(R.layout.specific_cost_item, parent, false)
//        transport_view = LayoutInflater.from(context).inflate(R.layout.specific_transport_item, parent, false)

//        place_item = view
//        place_parent = parent
        //mainView.setOnClickListener(onItemClick)
    }
    override fun getItemCount(): Int = specificItems.size

//    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: SpecificViewHolder, position: Int) {
        var imageList : ArrayList<Int> = ArrayList()
        imageList.add(R.drawable.tab_car)
        imageList.add(R.drawable.tab_walk)
        imageList.add(R.drawable.tab_train)
        imageList.add(R.drawable.tab_airplane)
        imageList.add(R.drawable.tab_bicycle)
        imageList.add(R.drawable.tab_bus)

        holder.specific_title.text = specificItems[position].title

        holder.itemView.memo.setOnClickListener {
            val intent : Intent = Intent(context, MemoActivity::class.java)
            var sendList : ArrayList<Int> = ArrayList()
            sendList.add(curDay)
            sendList.add(position)
            intent.putIntegerArrayListExtra("memo", sendList)
            (context as Activity).startActivityForResult(intent, MEMO_REQUEST)
            (context as Activity).finish()
        }

    if(TotalData.totalData.get(curDay) != null) {
        if (TotalData.totalData.get(curDay)!!.get(position).memo != null) {

            memo_layout = holder.itemView.findViewById(R.id.memo_item_layout) as LinearLayout
            memo_view = LayoutInflater.from(context).inflate(R.layout.specific_memo_item, cur_view, false)

            var memo_explain: TextView = memo_view.findViewById(R.id.memo_explain) as TextView
            var memo_img: ImageView = memo_view.findViewById(R.id.memo_img) as ImageView


            var data: Uri = TotalData.totalData.get(curDay)!!.get(position).memo!!.memo_img!!
            Log.v("SAdap", data.toString())
            memo_explain.text = TotalData.totalData.get(curDay)!!.get(position).memo!!.memo_title
            requestManager
                    .load(data)
                    .centerCrop()
                    .into(memo_img)
            memo_layout.setVisibility(View.VISIBLE)
            memo_layout.addView(memo_view)

        }
    }

        holder.itemView.cost.setOnClickListener {
            val intent : Intent = Intent((context as Activity), CostActivity::class.java)
            var sendList : ArrayList<Int> = ArrayList()
            sendList.add(curDay)
            sendList.add(position)
            intent.putIntegerArrayListExtra("cost", sendList)
            (context as Activity).startActivity(intent)
            (context as Activity).finish()
        }

    if(TotalData.totalData.get(curDay) != null) {
        if (TotalData.totalData.get(curDay)!!.get(position).cost != null) {


            cost_layout = holder.itemView.findViewById(R.id.cost_item_layout) as LinearLayout
            cost_view = LayoutInflater.from(context).inflate(R.layout.specific_cost_item, cur_view, false)

            var cost_comment: TextView = cost_view.findViewById(R.id.cost_comment) as TextView
            var cost_budget: TextView = cost_view.findViewById(R.id.cost_budget) as TextView

            cost_comment.text = TotalData.totalData.get(curDay)!!.get(position).cost!!.cost_comment
            cost_budget.text = TotalData.totalData.get(curDay)!!.get(position).cost!!.cost_budget

            cost_layout.setVisibility(View.VISIBLE)
            cost_layout.addView(cost_view)

        }
    }

        holder.itemView.transport.setOnClickListener {
            val intent : Intent = Intent((context as Activity), TransportActivity::class.java)
            var sendList : ArrayList<Int> = ArrayList()
            sendList.add(curDay)
            sendList.add(position)
            intent.putIntegerArrayListExtra("trans", sendList)
            (context as Activity).startActivity(intent)
            (context as Activity).finish()
        }
    if(TotalData.totalData.get(curDay) != null) {
        if (TotalData.totalData.get(curDay)!!.get(position).trans != null) {

            trans_layout = holder.itemView.findViewById(R.id.transport_item_layout) as LinearLayout
            trans_view = LayoutInflater.from(context).inflate(R.layout.specific_transport_item, cur_view, false)

            var transport_beginTime: TextView = trans_view.findViewById(R.id.transport_beginTime) as TextView
            var transport_endTime: TextView = trans_view.findViewById(R.id.transport_endTime) as TextView
            var transport_icon: ImageView = trans_view.findViewById(R.id.transport_icon) as ImageView
            var transport_explain: TextView = trans_view.findViewById(R.id.transport_explain) as TextView
            var transport_total: TextView = trans_view.findViewById(R.id.transport_total) as TextView

            transport_beginTime.text = TotalData.totalData.get(curDay)!!.get(position).trans!!.begin_time
            transport_endTime.text = TotalData.totalData.get(curDay)!!.get(position).trans!!.end_time
            transport_icon.setImageResource(imageList.get(TotalData.totalData.get(curDay)!!.get(position).trans!!.trans_img - 1))
            transport_explain.text = TotalData.totalData.get(curDay)!!.get(position).trans!!.trans_memo
            transport_total.text = TotalData.totalData.get(curDay)!!.get(position).trans!!.trans_cost

            trans_layout.setVisibility(View.VISIBLE)
            trans_layout.addView(trans_view)

        }
    }

        holder.itemView.specific_x_icon.setOnClickListener {
            var arrayList: ArrayList<Data> = TotalData.totalData.get(curDay)!!
            arrayList.removeAt(position)
            specificItems.removeAt(position)
            TotalData.totalData.put(curDay, arrayList)
            notifyDataSetChanged()
        }

    if(TotalData.totalData.get(curDay) != null) {
        if (TotalData.totalData.get(curDay)!!.get(position).memo != null) {
            holder.itemView.memo_item_x.setOnClickListener {
                var arrayList: ArrayList<Data> = TotalData.totalData.get(curDay)!!
                var tempData : Data = arrayList.get(position)
                var inputData : Data = Data(tempData.title, tempData.latitude, tempData.longtitude, null, tempData.cost, tempData.trans)
                arrayList.set(position, inputData)
                TotalData.totalData.put(curDay, arrayList)
                memo_layout.setVisibility(View.GONE)
            }
        }
    }

    if(TotalData.totalData.get(curDay) != null) {
        if (TotalData.totalData.get(curDay)!!.get(position).cost != null) {
            holder.itemView.cost_item_x.setOnClickListener {
                var arrayList: ArrayList<Data> = TotalData.totalData.get(curDay)!!
                var tempData : Data = arrayList.get(position)
                var inputData : Data = Data(tempData.title, tempData.latitude, tempData.longtitude, tempData.memo, null, tempData.trans)
                arrayList.set(position, inputData)
                TotalData.totalData.put(curDay, arrayList)
                cost_layout.setVisibility(View.GONE)

            }
        }
    }

    if(TotalData.totalData.get(curDay) != null) {
        if (TotalData.totalData.get(curDay)!!.get(position).trans != null) {
            holder.itemView.trans_item_x.setOnClickListener {
                var arrayList: ArrayList<Data> = TotalData.totalData.get(curDay)!!
                var tempData : Data = arrayList.get(position)
                var inputData : Data = Data(tempData.title, tempData.latitude, tempData.longtitude, tempData.memo, tempData.cost, null)
                arrayList.set(position, inputData)
                TotalData.totalData.put(curDay, arrayList)
                trans_layout.setVisibility(View.GONE)
            }
        }
    }

    }

}