package com.teamandroid.travelmaker.review

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.teamandroid.travelmaker.PostComment
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.TravelMakerApplication
import com.teamandroid.travelmaker.get.GetApplicationDetail
import kotlinx.android.synthetic.main.activity_apply_review.*
import kotlinx.android.synthetic.main.apply_item_inside.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApplyReview : AppCompatActivity(), View.OnClickListener {

    lateinit var comment : ArrayList<Comment>
    var board_idx = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_review)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.parseColor("#14DAC6")
        }

        comment_regist.setOnClickListener(this)
        board_idx = intent.getIntExtra("board_idx",-1)
        requestApplyData(board_idx)

    }

    override fun onClick(v: View?) {
        if(v == comment_regist){

            if(comment_contents.text.toString().trim().isNotEmpty()){
                val postComment = (applicationContext as TravelMakerApplication).getApplicationNetworkService().postComment(
                        (applicationContext as TravelMakerApplication).userToken,
                        board_idx,
                        comment_contents.text.toString().trim()
                )
                comment_contents.setText("")
                postComment.enqueue(object : Callback<PostComment>{
                    override fun onFailure(call: Call<PostComment>?, t: Throwable?) {
                        t!!.printStackTrace()
                    }

                    override fun onResponse(call: Call<PostComment>?, response: Response<PostComment>?) {
                        if(response!!.isSuccessful){
                            Log.d("ㅎ","성공")
                            val getApplicationDetail = (applicationContext as TravelMakerApplication).getApplicationNetworkService().getdetailApplication(board_idx)

                            getApplicationDetail.enqueue(object : Callback<GetApplicationDetail>{
                                override fun onFailure(call: Call<GetApplicationDetail>?, t: Throwable?) {

                                }

                                override fun onResponse(call: Call<GetApplicationDetail>?, response: Response<GetApplicationDetail>?) {
                                    if(response!!.isSuccessful){
                                        comment = response.body()!!.comment

                                        Log.d("innn",comment.size.toString())
                                        comment_recycler.adapter = CommentRecyclerAdapter(comment)
                                        comment_recycler.layoutManager = LinearLayoutManager(applicationContext)
                                    }
                                }

                            })
                        }

                    }

                })
            }
        }
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK,intent)
        super.onBackPressed()
    }

    fun requestApplyData(board_idx : Int){
        val getApplicationDetail = (applicationContext as TravelMakerApplication).getApplicationNetworkService().getdetailApplication(board_idx)

        getApplicationDetail.enqueue(object : Callback<GetApplicationDetail>{
            override fun onFailure(call: Call<GetApplicationDetail>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<GetApplicationDetail>?, response: Response<GetApplicationDetail>?) {
                if(response!!.isSuccessful){
                    val board = response.body()!!.board
                    comment = response.body()!!.comment

                    apply_title.setText("["+board[0].board_city +"] "+board[0].board_title)
                    apply_id.text = response.body()!!.sender[0].user_nick
                    apply_time.text = board[0].board_writetime

                    apply_during.text = board[0].board_dep_time + " ~ " + board[0].board_arr_time
                    apply_day.text = board[0].board_days.toString() + "일"
                    apply_coin.text = board[0].board_coin.toString() + "코인"
                    apply_etc.text = board[0].board_content

                    apply_item_recycler.adapter = PlanRecyclerAdapter(response.body()!!.plan)
                    apply_item_recycler.layoutManager = LinearLayoutManager(applicationContext)

                    Log.d("innn",comment.size.toString())
                    comment_recycler.adapter = CommentRecyclerAdapter(comment)
                    comment_recycler.layoutManager = LinearLayoutManager(applicationContext)
                }
            }

        })
    }
}
