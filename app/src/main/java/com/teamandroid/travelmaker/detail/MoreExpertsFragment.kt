package com.teamandroid.travelmaker.detail

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.*
import com.teamandroid.travelmaker.etc.FilterDialogFragment
import com.teamandroid.travelmaker.main.Country
import com.teamandroid.travelmaker.review.ApplyReview
import kotlinx.android.synthetic.main.apply_layout.view.*
import kotlinx.android.synthetic.main.expert_person_item.view.*
import kotlinx.android.synthetic.main.fragment_apply.view.*
import kotlinx.android.synthetic.main.fragment_expert.view.*
import java.util.ArrayList

class MoreExpertsFragment : Fragment(),View.OnClickListener {
    lateinit var mView : View
    lateinit var experts : ArrayList<Expert>

    companion object {
        fun newInstance(experts: ArrayList<Expert>): MoreExpertsFragment {
            val fragment = MoreExpertsFragment()
            fragment.experts = experts
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_expert,container,false)
        (activity as DetailActivity).otherDesign()
        mView.expert_filter.setOnClickListener(this)
        mView.expert_recycler.adapter = MoreExpertsRecyclerAdapter(experts)
        mView.expert_recycler.layoutManager = LinearLayoutManager(activity!!.applicationContext)

        mView.expert_recycler.addOnItemTouchListener(RecyclerItemClickListener(activity!!.applicationContext, mView.expert_recycler,
                object : RecyclerItemClickListener.OnItemClickListener{
                    override fun onItemClick(view: View, position: Int) {
                        val intent = Intent(activity!!.applicationContext, ExpertActivity::class.java)
                        intent.putExtra("expert_idx",experts[position].user_idx)
                        intent.putExtra("expert_grade",experts[position].expert_grade)
                        intent.putExtra("country_idx",8)
                        startActivity(intent)
                    }

                    override fun onLongItemClick(view: View, position: Int) {
                    }
                }))

        return mView
    }

    override fun onClick(v: View?) {
        if(mView.expert_filter == v){
            val filterDialogFragment = FilterDialogFragment()
            filterDialogFragment.show(activity!!.supportFragmentManager,null)
        }
    }
}