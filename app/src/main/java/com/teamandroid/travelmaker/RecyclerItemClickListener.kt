package com.teamandroid.travelmaker

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

class RecyclerItemClickListener(mcontext : Context, var recyclerView: RecyclerView, var listener : OnItemClickListener) : RecyclerView.OnItemTouchListener{

    interface OnItemClickListener{
        fun onItemClick(view : View, position : Int)
        fun onLongItemClick(view : View, position : Int)
    }

    var mGestureDetector: GestureDetector

    init {
        mGestureDetector = GestureDetector(mcontext,object : GestureDetector.SimpleOnGestureListener(){
            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                return true
            }

            override fun onLongPress(e: MotionEvent?) {
                val child = recyclerView.findChildViewUnder(e!!.x, e.y)
                if (child != null) {
                    listener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child))
                }
            }
        } )
    }




    override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {

    }

    override fun onInterceptTouchEvent(rv: RecyclerView?, e: MotionEvent?): Boolean {
        val childView = rv!!.findChildViewUnder(e!!.x, e.y)
        if (childView != null && mGestureDetector.onTouchEvent(e)) {
            listener.onItemClick(childView, rv.getChildAdapterPosition(childView))
            return true
        }
        return false

    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

    }
}