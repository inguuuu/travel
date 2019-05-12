package com.teamandroid.travelmaker.main.mypage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.R
import kotlinx.android.synthetic.main.fragment_mypage.view.*

class MyPageFragment : Fragment() {
    lateinit var mView : View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_mypage,container, false)

        mView.mypageBtn.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.myPageFrameLayout,FragmentEnviroment()).commit()
        }
        return mView
    }

}