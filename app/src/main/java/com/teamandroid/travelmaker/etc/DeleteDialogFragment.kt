package com.teamandroid.travelmaker.etc

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.R
import kotlinx.android.synthetic.main.delete_dialog.*
import kotlinx.android.synthetic.main.delete_dialog.view.*

class DeleteDialogFragment : DialogFragment() {

    lateinit var mView: View
    lateinit var okListener: View.OnClickListener
    lateinit var cancelListener:View.OnClickListener
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mView = inflater.inflate(R.layout.delete_dialog, container, false)

        mView.btn_ok.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                okListener.onClick(v)
                dismiss()
            }

        })
        mView.btn_cancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                cancelListener.onClick(v)
                dismiss()
            }

        })
        return mView
    }

    override fun onResume() {
        super.onResume()
        val window = dialog!!.window
        val width = resources.getDimension(R.dimen.delete_dialog_width).toInt()
        val height = resources.getDimension(R.dimen.delete_dialog_height).toInt()
        window.setLayout(width, height)
        window.setBackgroundDrawable(resources.getDrawable(R.drawable.white_box,null))
        window.setGravity(Gravity.CENTER)
    }


    fun setOkButton(OkListener: View.OnClickListener) {
        this.okListener = OkListener
    }

    fun setcancelButton(cancelListener: View.OnClickListener) {
        this.cancelListener = cancelListener
    }
}