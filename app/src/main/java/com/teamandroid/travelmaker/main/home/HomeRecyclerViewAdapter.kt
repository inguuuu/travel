package com.teamandroid.travelmaker.main.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamandroid.travelmaker.R
import android.content.Context
import android.graphics.Point
import android.util.Log
import android.view.View
import com.teamandroid.travelmaker.etc.InfinitePagerAdapter
import com.teamandroid.travelmaker.main.Category
import com.teamandroid.travelmaker.main.MainActivity


class HomeRecyclerViewAdapter(var categories: ArrayList<Category>, var activity: MainActivity) : RecyclerView.Adapter<HomeRecyclerViewHolder>(){

    lateinit var mContext : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_recyclerview_item, parent, false)
        mContext = parent.context
        Log.d("num",categories.size.toString())
        return HomeRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) {
        holder.viewPager.id = position + 1
        holder.viewPager.adapter = InfinitePagerAdapter(CountryThumbnailAdapter(categories[position].country, activity))
        holder.viewPager.setPadding(350, 0, 350, 0)
        holder.viewPager.offscreenPageLimit = 9

        val screen = Point()
        activity.windowManager.defaultDisplay.getSize(screen)

        val startOffset = 350.0f / (screen.x - 2 * 350.0f)
        holder.viewPager.setPageTransformer(false, CardPagerTransformerShift(holder.viewPager.elevation * 1.3f, holder.viewPager.elevation,
                    0.6f, startOffset))
        holder.countryName.text = categories[position].name

        if(position + 1 == itemCount){
            holder.footer.visibility = View.INVISIBLE
        }
    }

}