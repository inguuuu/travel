package com.teamandroid.travelmaker.etc

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import java.io.File

class InfinitePagerAdapter(var adapter: PagerAdapter) : PagerAdapter() {


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
       return adapter.isViewFromObject(view, `object`)
    }

    override fun getCount(): Int {
        if(getRealCount() == 0){
            return 0
        }

        return Integer.MAX_VALUE
    }

    fun getRealCount() : Int{
        return adapter.count
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var virtualPosition = position % getRealCount()

        return adapter.instantiateItem(container, virtualPosition)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        var virtualPosition = position % getRealCount()

        adapter.destroyItem(container, virtualPosition, `object`)
    }

    override fun getItemPosition(`object`: Any): Int {
        return adapter.getItemPosition(`object`)
    }
}