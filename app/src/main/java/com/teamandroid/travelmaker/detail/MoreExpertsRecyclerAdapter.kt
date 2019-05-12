package com.teamandroid.travelmaker.detail

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamandroid.travelmaker.Application
import com.teamandroid.travelmaker.Expert
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.R.id.*
import java.util.*

class MoreExpertsRecyclerAdapter(var item : ArrayList<Expert>) : RecyclerView.Adapter<MoreExpertsRecyclerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreExpertsRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.expert_person_item, parent, false)

        return MoreExpertsRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: MoreExpertsRecyclerViewHolder, position: Int) {

        if(position == 0){
            holder.profile.setImageResource(R.drawable.china_expert_img)
        }else if(position == 1){
            holder.profile.setImageResource(R.drawable.expert_img_1)
        }else if(position == 2){
            holder.profile.setImageResource(R.drawable.expert_img_2)
        }else{
            holder.profile.setImageResource(R.drawable.expert_img_3)
        }


        var city = ""

        if(item[position].expert_city1 != null){
            val token = StringTokenizer(item[position].expert_city1, " ")
            var string = token.nextToken()

            if(string.compareTo("중국") == 0){
                if(token.hasMoreTokens()){
                    string = token.nextToken()
                    city = "" + string + " "
                }
            }
        }

        if(item[position].expert_city2 != null){
            val token = StringTokenizer(item[position].expert_city2, " ")
            var string = token.nextToken()

            if(string.compareTo("중국") == 0){
                if(token.hasMoreTokens()){
                    string = token.nextToken()
                    city = "" + string + " "
                }
            }
        }

        if(item[position].expert_city3 != null){
            val token = StringTokenizer(item[position].expert_city3, " ")
            var string = token.nextToken()

            if(string.compareTo("중국") == 0){
                if(token.hasMoreTokens()){
                    string = token.nextToken()
                    city = "" + string + " "
                }
            }
        }

        holder.city.text = city

        var img = 0
        when(item[position].expert_grade){
            0 -> img = R.drawable.blue_crown_big
            1 -> img = R.drawable.emerald_crown
            2 -> img = R.drawable.gold_crown_big
        }

        holder.crown.setImageResource(img)

        holder.name.text = item[position].user_nick

        var style = ""
        when(item[position].user_style){
            0 -> style = "콜럼버스형"
            1 -> style = "인생샷형"
            2 -> style = "액티비티형"
            3 -> style = "먹고죽자형"
            4 -> style = "느긋한 휴양자형"
            5 -> style = "자린고비형"
            6 -> style = "쇼핑형"
        }

        holder.tendency.text = style

        holder.ratingBar.rating = (item[position].expert_rate)!!.toFloat()
        holder.ratingValue.text = "("+item[position].expert_rate.toString()+")"
    }
}