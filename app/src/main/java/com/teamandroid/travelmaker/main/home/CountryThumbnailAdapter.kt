package com.teamandroid.travelmaker.main.home

import android.content.Intent
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.main.Country
import com.teamandroid.travelmaker.main.MainActivity
import com.teamandroid.travelmaker.detail.CountryDetailFragment
import com.teamandroid.travelmaker.detail.DetailActivity
import kotlinx.android.synthetic.main.home_viewpager_item.view.*

class CountryThumbnailAdapter(var countries : ArrayList<Country>, var activity: MainActivity) : PagerAdapter(), View.OnClickListener {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int  = countries.size

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.home_viewpager_item, container,false)

        view.rc_vp_countryImage.setImageBitmap(countries[position].thumbnailbitmap)
        view.setOnClickListener(this)
        view.isClickable = false
        view.tag = position
        view.rc_vp_countryName.text = countries[position].countryData.country_name
        container.addView(view)
        return view
    }

    override fun onClick(v: View?) {
        val position = v!!.tag as Int
        val intent = Intent(activity.applicationContext, DetailActivity::class.java)
        intent.putExtra("countryData",countries[position].countryData)
        activity.startActivity(intent)
    }
}