package com.teamandroid.travelmaker.help

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import com.teamandroid.travelmaker.R
import kotlinx.android.synthetic.main.activity_help.*

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        val fragmentManager : FragmentManager = supportFragmentManager
        val adapter = SwipeAdapter(fragmentManager)
        help_frame.adapter = adapter
    }
}
