package com.teamandroid.travelmaker.travelplanread
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.net.Uri
import android.util.Log
import com.bumptech.glide.RequestManager
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.travelplanwrite.dataSets.TotalData

class GetSpecificAdapter(var specificItems : ArrayList<GetSpecificItem>, var plan : GetPlan,day : String, c : Context, var requestManager: RequestManager) : RecyclerView.Adapter<GetSpecificViewHolder>() {
    lateinit var place_item : View
    lateinit var cur_view : ViewGroup
    var context : Context = c
    var curDay : Int = day.toInt()

    lateinit var place_parent : ViewGroup
    lateinit var memoItems : ArrayList<GetMemoItem>
    lateinit var costItems : ArrayList<GetCostItem>
    lateinit var transportItems : ArrayList<GetTransportItem>
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetSpecificViewHolder {
        val view : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.get_specific_place_item, parent, false)
        cur_view = view as ViewGroup

        return GetSpecificViewHolder(view)

//        memo_view = LayoutInflater.from(context).inflate(R.layout.specific_memo_item, parent, false)
//        cost_view = LayoutInflater.from(context).inflate(R.layout.specific_cost_item, parent, false)
//        transport_view = LayoutInflater.from(context).inflate(R.layout.specific_transport_item, parent, false)

//        place_item = view
//        place_parent = parent
        //mainView.setOnClickListener(onItemClick)

    }
    override fun getItemCount(): Int = plan.trans.size

    //    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: GetSpecificViewHolder, position: Int) {
        var imageList : ArrayList<Int> = ArrayList()
        imageList.add(R.drawable.tab_car)
        imageList.add(R.drawable.tab_walk)
        imageList.add(R.drawable.tab_train)
        imageList.add(R.drawable.tab_airplane)
        imageList.add(R.drawable.tab_bicycle)
        imageList.add(R.drawable.tab_bus)

        holder.specific_title.text = specificItems[position].title

//        holder.itemView.memo.setOnClickListener {
//            val intent : Intent = Intent(context, MemoActivity::class.java)
//            var sendList : ArrayList<Int> = ArrayList()
//            sendList.add(curDay)
//            sendList.add(position)
//            intent.putIntegerArrayListExtra("memo", sendList)
//            (context as Activity).startActivityForResult(intent, MEMO_REQUEST)
//            (context as Activity).finish()
//        }


                memo_layout = holder.itemView.findViewById(R.id.memo_item_layout) as LinearLayout
                memo_view = LayoutInflater.from(context).inflate(R.layout.get_specific_memo_item, cur_view, false)

                var memo_explain: TextView = memo_view.findViewById(R.id.memo_explain) as TextView
                var memo_img: ImageView = memo_view.findViewById(R.id.memo_img) as ImageView

                if(plan.place[position].place_img != null){

                    var data: Uri = Uri.parse(plan.place[position].place_img)

                    Log.d("parse",data.toString())
                    requestManager
                            .load(data)
                            .centerCrop()
                            .into(memo_img)
                    memo_layout.setVisibility(View.VISIBLE)
                    memo_layout.addView(memo_view)

                }

                memo_explain.text = plan.place[position].place_comment

//        holder.itemView.cost.setOnClickListener {
//            val intent : Intent = Intent((context as Activity), CostActivity::class.java)
//            var sendList : ArrayList<Int> = ArrayList()
//            sendList.add(curDay)
//            sendList.add(position)
//            intent.putIntegerArrayListExtra("cost", sendList)
//            (context as Activity).startActivity(intent)
//            (context as Activity).finish()
//        }


                cost_layout = holder.itemView.findViewById(R.id.cost_item_layout) as LinearLayout
                cost_view = LayoutInflater.from(context).inflate(R.layout.get_specific_cost_item, cur_view, false)

                var cost_comment: TextView = cost_view.findViewById(R.id.cost_comment) as TextView
                var cost_budget: TextView = cost_view.findViewById(R.id.cost_budget) as TextView

                cost_comment.text = plan.place[position].place_budget_comment

        Log.d("ss",cost_comment.text.toString())
                cost_budget.text = plan.place[position].place_budget.toString()

                cost_layout.setVisibility(View.VISIBLE)
                cost_layout.addView(cost_view)


//        holder.itemView.transport.setOnClickListener {
//            val intent : Intent = Intent((context as Activity), TransportActivity::class.java)
//            var sendList : ArrayList<Int> = ArrayList()
//            sendList.add(curDay)
//            sendList.add(position)
//            intent.putIntegerArrayListExtra("trans", sendList)
//            (context as Activity).startActivity(intent)
//            (context as Activity).finish()
//        }


                trans_layout = holder.itemView.findViewById(R.id.transport_item_layout) as LinearLayout
                trans_view = LayoutInflater.from(context).inflate(R.layout.get_specific_transport_item, cur_view, false)

                var transport_beginTime: TextView = trans_view.findViewById(R.id.transport_beginTime) as TextView
                var transport_endTime: TextView = trans_view.findViewById(R.id.transport_endTime) as TextView
                var transport_icon: ImageView = trans_view.findViewById(R.id.transport_icon) as ImageView
                var transport_explain: TextView = trans_view.findViewById(R.id.transport_explain) as TextView
                var transport_total: TextView = trans_view.findViewById(R.id.transport_total) as TextView

                transport_beginTime.text = plan.trans[position].trans_dep_time
                transport_endTime.text =  plan.trans[position].trans_arr_time


                transport_icon.setImageResource(imageList.get(plan.trans[position].trans_name))
                transport_explain.text = plan.trans[position].trans_content
                transport_total.text = plan.trans[position].trans_budget.toString()

                trans_layout.setVisibility(View.VISIBLE)
                trans_layout.addView(trans_view)

    }
}