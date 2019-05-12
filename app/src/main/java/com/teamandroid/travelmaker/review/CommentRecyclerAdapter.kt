package com.teamandroid.travelmaker.review

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.R

class CommentRecyclerAdapter(var items : ArrayList<Comment>) : RecyclerView.Adapter<CommentRecyclerViewHolder>(){

    lateinit var mContext: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        mContext = parent.context
        return CommentRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CommentRecyclerViewHolder, position: Int) {
        holder.nickName.text = items[position].user_info
        holder.contents.text = items[position].comment_data.comment_content

        holder.btn_expert.setOnClickListener {
            val intent = Intent(mContext, ApplyReview::class.java)
            intent.putExtra("board_idx",items[position].comment_data.board_idx)

        }
    }

}