package com.teamandroid.travelmaker.travelplanwrite.Memo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamandroid.travelmaker.R


class MemoAdapter (var memoItems : ArrayList<MemoItem>) : RecyclerView.Adapter<MemoViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.specific_memo_item, parent, false)
        return MemoViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return memoItems.size
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
//        holder.memo_img = memoItems[position].memo_img as ImageView
        holder.memo_explain.text = memoItems[position].memo_explain
        holder.memo_img.setImageResource(memoItems[position].memo_img)
    }
}