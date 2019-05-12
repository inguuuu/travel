package com.teamandroid.travelmaker.etc

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.R

class FilterDialogFragment : DialogFragment() {

    lateinit var mView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mView = inflater.inflate(R.layout.filter_dialog, container, false)
        return mView
    }

    override fun onResume() {
        super.onResume()
        val window = dialog!!.window
        val width = resources.getDimension(R.dimen.filter_dialog_width).toInt()
        val height = resources.getDimension(R.dimen.filter_dialog_height).toInt()
        window.setLayout(width, height)
        window.setBackgroundDrawable(resources.getDrawable(R.drawable.white_box,null))
        window.setGravity(Gravity.CENTER)
    }

}