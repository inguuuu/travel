package com.teamandroid.travelmaker.detail

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.Application
import com.teamandroid.travelmaker.Expert
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.RecyclerItemClickListener
import com.teamandroid.travelmaker.etc.DeleteDialogFragment
import com.teamandroid.travelmaker.etc.FilterDialogFragment
import com.teamandroid.travelmaker.main.MainActivity
import com.teamandroid.travelmaker.main.send.SendRecyclerViewAdapter
import com.teamandroid.travelmaker.review.ApplyReview
import com.teamandroid.travelmaker.write.ApplyWrite
import kotlinx.android.synthetic.main.apply_layout.view.*
import kotlinx.android.synthetic.main.fragment_apply.view.*
import kotlinx.android.synthetic.main.fragment_send.view.*
import java.util.ArrayList

class MoreApplicationsFragment : Fragment() , View.OnClickListener{

    lateinit var applications : ArrayList<Application>

    companion object {
        fun newInstance(applications: ArrayList<Application>): MoreApplicationsFragment {
            val fragment = MoreApplicationsFragment()
            fragment.applications = applications
            return fragment
        }
    }
    override fun onClick(v: View?) {
        if(mView.btn_filter == v){
            val filterDialogFragment = FilterDialogFragment()
            filterDialogFragment.show(activity!!.supportFragmentManager,null)
        }
        else if(v == mView.gotoApply){
            val intent = Intent(activity!!.applicationContext, ApplyWrite::class.java)
            startActivity(intent)
        }
    }

    lateinit var mView : View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_apply,container,false)
        (activity as DetailActivity).otherDesign()
        mView.btn_filter.setOnClickListener(this)
        mView.gotoApply.setOnClickListener(this)
        mView.application_recycler.adapter = MoreApplicationsRecyclerAdapter(applications)
        mView.application_recycler.layoutManager = LinearLayoutManager(activity!!.applicationContext)

        mView.application_recycler.addOnItemTouchListener(RecyclerItemClickListener(activity!!.applicationContext, mView.application_recycler,
                object : RecyclerItemClickListener.OnItemClickListener{
                    override fun onItemClick(view: View, position: Int) {
                        val intent = Intent(activity!!.applicationContext, ApplyReview::class.java)
                        intent.putExtra("board_idx",applications[position].board_idx)
                        startActivity(intent)
                    }

                    override fun onLongItemClick(view: View, position: Int) {
                    }
                }))


        return mView
    }
}