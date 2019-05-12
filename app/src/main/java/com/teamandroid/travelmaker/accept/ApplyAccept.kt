package com.teamandroid.travelmaker.accept

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.TravelMakerApplication
import com.teamandroid.travelmaker.get.GetApplicationDetail
import com.teamandroid.travelmaker.post.PostApplicationAccept
import com.teamandroid.travelmaker.post.PostApplicationReject
import com.teamandroid.travelmaker.review.Board
import com.teamandroid.travelmaker.review.CommentRecyclerAdapter
import com.teamandroid.travelmaker.review.PlanRecyclerAdapter
import kotlinx.android.synthetic.main.activity_apply_accept.*
import kotlinx.android.synthetic.main.activity_apply_review.*
import kotlinx.android.synthetic.main.apply_item_inside.*
import kotlinx.android.synthetic.main.delete_dialog.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApplyAccept : AppCompatActivity(), View.OnClickListener {
    lateinit var board : ArrayList<Board>
    var board_idx = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_accept)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.parseColor("#14DAC6")
        }

        _btn_close.setOnClickListener(this)
        accept_btn_cancel.setOnClickListener(this)
        accept_btn_ok.setOnClickListener(this)
        board_idx = intent.getIntExtra("board_idx",-1)
        requestApplyData(board_idx)

    }


    fun requestApplyData(board_idx : Int){
        val getApplicationDetail = (applicationContext as TravelMakerApplication).getApplicationNetworkService().getdetailApplication(board_idx)

        getApplicationDetail.enqueue(object : Callback<GetApplicationDetail> {
            override fun onFailure(call: Call<GetApplicationDetail>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<GetApplicationDetail>?, response: Response<GetApplicationDetail>?) {
                if(response!!.isSuccessful){
                    board = response.body()!!.board

                    apply_title.setText("["+board[0].board_city +"] "+board[0].board_title)
                    apply_id.text = response.body()!!.sender[0].user_nick
                    apply_time.text = board[0].board_writetime

                    apply_during.text = board[0].board_arr_time + " ~ " + board[0].board_dep_time
                    apply_day.text = board[0].board_days.toString() + "일"
                    apply_coin.text = board[0].board_coin.toString() + "코인"
                    apply_etc.text = board[0].board_content

                    apply_item_recycler.adapter = PlanRecyclerAdapter(response.body()!!.plan)
                    apply_item_recycler.layoutManager = LinearLayoutManager(applicationContext)

                }
            }

        })
    }

    override fun onClick(v: View?) {
        if(v == _btn_close){
            onBackPressed()
        }
        else if(v == accept_btn_cancel){
            val postApplicationReject = (applicationContext as TravelMakerApplication).getApplicationNetworkService().postApplicationReject(
                    (applicationContext as TravelMakerApplication).userToken,
                    board_idx
            )

            postApplicationReject.enqueue(object : Callback<PostApplicationReject>{
                override fun onFailure(call: Call<PostApplicationReject>?, t: Throwable?) {

                }

                override fun onResponse(call: Call<PostApplicationReject>?, response: Response<PostApplicationReject>?) {
                    finish()
                }

            })
        }
        else if(v == accept_btn_ok){
            val postApplicationAccept = (applicationContext as TravelMakerApplication).getApplicationNetworkService().postApplicationAccept(
                    (applicationContext as TravelMakerApplication).userToken,
                    board[0].board_idx,
                    board[0].board_coin,
                    board[0].user_idx
            )

            postApplicationAccept.enqueue(object : Callback<PostApplicationAccept>{
                override fun onFailure(call: Call<PostApplicationAccept>?, t: Throwable?) {

                }

                override fun onResponse(call: Call<PostApplicationAccept>?, response: Response<PostApplicationAccept>?) {
                    finish()
                }

            })
        }
    }
}
