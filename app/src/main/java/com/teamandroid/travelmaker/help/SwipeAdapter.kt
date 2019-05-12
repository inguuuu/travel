package com.teamandroid.travelmaker.help

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class SwipeAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {

    val imageCount = 4

    override fun getCount(): Int {
        return imageCount
    }

    override fun getItem(position: Int) : Fragment {

        when (position) {

            0 -> return HelpFragment1()
            1-> return HelpFragment2()
            2-> return HelpFragment3()
          else -> return HelpFragment4()

        }

    }
}