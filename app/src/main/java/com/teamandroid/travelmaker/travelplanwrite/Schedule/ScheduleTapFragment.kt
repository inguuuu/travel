package com.teamandroid.travelmaker.travelplanwrite.Schedule

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView
import com.teamandroid.travelmaker.Acc
import com.teamandroid.travelmaker.Inn
import com.teamandroid.travelmaker.Out
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.review.Board
import com.teamandroid.travelmaker.travelplanwrite.dataSets.TotalData
import com.teamandroid.travelmaker.write.ApplyWriteViewHolder
import kotlinx.android.synthetic.main.activity_apply_write.*
import kotlinx.android.synthetic.main.schedule_tab.*
import kotlinx.android.synthetic.main.schedule_tab.view.*


class ScheduleTapFragment : Fragment() {
    private val mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    lateinit var scheduleItems : ArrayList<ScheduleItem>
    lateinit var activity_context : Context
    lateinit var board : Board
    lateinit var  totalCost : TextView
    lateinit var mview : View
    var totalCostValue = 0
    companion object {
        fun newInstance(board : Board) : ScheduleTapFragment{
            val fragment = ScheduleTapFragment()
            fragment.board = board
            return fragment
        }
    }

    override fun onStart() {
        super.onStart()


            for(i in 0..TotalData.totalData.size-1){
                for(data in TotalData.totalData.get(i+1)!!){
                    if(data.cost != null ){
                        totalCostValue += data.cost!!.cost_budget.toInt()
                    }
                    if(data.trans != null){
                        totalCostValue += data.trans!!.trans_cost.toInt()
                    }
                }
            }
        mview.totalCost!!.text = totalCostValue.toString() + " KRW"

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity_context = container!!.context
        mview = inflater.inflate(R.layout.schedule_tab, container, false)
        scheduleItems = ArrayList()

        var totalCostValue = 0
        mview.totalCost!!.text = totalCostValue.toString() + " KRW"



        for(i in 0..(board.board_days - 1)){
            scheduleItems.add(ScheduleItem(i+1, "작성하기"))
        }


        // Set the adapter

        if (mview.rv_schedule is RecyclerView) {

            mview.rv_schedule.setHasFixedSize(true)

            // specify an adapter (see also next example)

            mAdapter = ScheduleAdapter(scheduleItems, activity_context)
            mview.rv_schedule.adapter = mAdapter

            mLayoutManager = LinearLayoutManager(context)
            mview.rv_schedule.layoutManager = mLayoutManager

            Log.d("board",board.board_days.toString())

        }
        return mview
    }
}