package com.teamandroid.travelmaker.main.send

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.RecyclerItemClickListener
import com.teamandroid.travelmaker.TravelMakerApplication
import com.teamandroid.travelmaker.etc.DeleteDialogFragment
import com.teamandroid.travelmaker.main.MainActivity
import com.teamandroid.travelmaker.post.PostSendApplication
import com.teamandroid.travelmaker.review.ApplyReview
import com.teamandroid.travelmaker.travelplanread.GetPlanActivity
import com.teamandroid.travelmaker.write.ApplyWrite
import kotlinx.android.synthetic.main.fragment_send.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SendFragment : Fragment() {

    lateinit var mView : View
    lateinit var items : ArrayList<SendBoard>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mView = inflater.inflate(R.layout.fragment_send,container,false)
        (activity as MainActivity).initActivityDesign()

        requestSendApplication()
        return mView
    }

    fun requestSendApplication(){
        val postSendApplication = (activity!!.applicationContext as TravelMakerApplication).getApplicationNetworkService().getSendApplication(
                (activity!!.applicationContext as TravelMakerApplication).userToken
        )

        postSendApplication.enqueue(object : Callback<PostSendApplication>{
            override fun onFailure(call: Call<PostSendApplication>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<PostSendApplication>?, response: Response<PostSendApplication>?) {
                if(response!!.isSuccessful) {
                    items = response.body()!!.receive_board

                    mView.send_recycler.adapter = SendRecyclerViewAdapter(items)
                    mView.send_recycler.layoutManager = LinearLayoutManager(activity!!.applicationContext)
                    mView.send_recycler.addOnItemTouchListener(RecyclerItemClickListener(activity!!.applicationContext, mView.send_recycler,
                            object : RecyclerItemClickListener.OnItemClickListener {
                                override fun onItemClick(view: View, position: Int) {

                                    if(items[position].board_data.board_status == 4){
                                        val intent = Intent(activity!!.applicationContext, GetPlanActivity::class.java)
                                        intent.putExtra("board_idx", items[position].board_data.board_idx)
                                        startActivity(intent)
                                    }else {
                                        val intent = Intent(activity!!.applicationContext, ApplyReview::class.java)
                                        intent.putExtra("board_idx", items[position].board_data.board_idx)
                                        startActivity(intent)
                                    }
                                }

                                override fun onLongItemClick(view: View, position: Int) {

                                }
                            }))
                }
            }

        })
    }
}