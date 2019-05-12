package com.teamandroid.travelmaker.main.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.main.Category
import com.teamandroid.travelmaker.main.MainActivity
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
    lateinit var categories : ArrayList<Category>

    companion object {
        fun newInstance(categories : ArrayList<Category>): HomeFragment {
            val fragment = HomeFragment()
            fragment.categories = categories
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home,container,false)
        Log.d("num",categories.size.toString())
        view.home_recyclerView.adapter = HomeRecyclerViewAdapter(categories, activity as MainActivity)
        view.home_recyclerView.layoutManager = LinearLayoutManager(container!!.context)

        (activity as MainActivity).initActivityDesign()
        return view
    }
}