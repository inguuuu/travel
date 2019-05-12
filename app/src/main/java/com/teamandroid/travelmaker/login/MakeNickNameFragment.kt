package com.teamandroid.travelmaker.login

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.teamandroid.travelmaker.NaverUserInfo
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.TravelMakerApplication
import com.teamandroid.travelmaker.main.MainActivity
import com.teamandroid.travelmaker.post.PostSignIn
import com.teamandroid.travelmaker.post.PostSignup
import kotlinx.android.synthetic.main.fragment_nickname.*
import kotlinx.android.synthetic.main.fragment_nickname.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MakeNickNameFragment : Fragment(), View.OnClickListener {

    var currentTendency : TextView? = null
    lateinit var naverUserInfo: NaverUserInfo
    lateinit var  mView : View
    companion object {
        fun newInstance(naverUserInfo : NaverUserInfo): MakeNickNameFragment {
            val fragment = MakeNickNameFragment()
            fragment.naverUserInfo = naverUserInfo
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_nickname,container, false)

        mView.button1.tag = "3"
        mView.button2.tag = "0"
        mView.button3.tag = "1"
        mView.button4.tag = "4"
        mView.button5.tag = "2"
        mView.button6.tag = "5"
        mView.button7.tag = "6"

        mView.button1.setOnClickListener(this)
        mView.button2.setOnClickListener(this)
        mView.button3.setOnClickListener(this)
        mView.button4.setOnClickListener(this)
        mView.button5.setOnClickListener(this)
        mView.button6.setOnClickListener(this)
        mView.button7.setOnClickListener(this)

        mView.signup.setOnClickListener(this)

        mView.nickname_edit.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mView.nickname_edit.hint = ""
            }

        })

        return mView
    }

    override fun onClick(v: View?) {
        if(v == mView.signup){
            if(nickname_edit.text.trim().isNotEmpty() && currentTendency != null){
                naverUserInfo.user_nick = nickname_edit.text.toString()
                naverUserInfo.user_style = currentTendency!!.tag as String
                requestSignUp()
            }
        }else if(v == mView.nickname_check){

        }else{
            if(currentTendency != null){
                currentTendency!!.isSelected = false
            }

            when(v){
                v!!.button1 -> currentTendency = button1
                v.button2 -> currentTendency = button2
                v.button3 -> currentTendency = button3
                v.button4 -> currentTendency = button4
                v.button5 -> currentTendency = button5
                v.button6 -> currentTendency = button6
                v.button7 -> currentTendency = button7
            }

            currentTendency!!.isSelected = true
        }
    }


    fun requestSignUp(){
        val postSignup = (activity!!.applicationContext as TravelMakerApplication).getApplicationNetworkService().postUserSignup(
                naverUserInfo.user_id,
                naverUserInfo.user_name,
                naverUserInfo.user_age,
                naverUserInfo.user_gender,
                naverUserInfo.user_nick,
                naverUserInfo.user_email,
                naverUserInfo.user_style,
                naverUserInfo.user_img
        )

        postSignup.enqueue(object : Callback<PostSignup> {
            override fun onFailure(call: Call<PostSignup>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<PostSignup>?, response: Response<PostSignup>?) {
                if (response!!.code() != 500) {
                        Log.d("ok","ok")
                        val postSignIn = (activity!!.applicationContext as TravelMakerApplication).getApplicationNetworkService().postUserSignIn(naverUserInfo.user_id)

                        postSignIn.enqueue(object : Callback<PostSignIn> {
                            override fun onFailure(call: Call<PostSignIn>?, t: Throwable?) {
                            }

                            override fun onResponse(call: Call<PostSignIn>?, response: Response<PostSignIn>?) {
                                if (response!!.isSuccessful) {
                                        (activity!!.applicationContext as TravelMakerApplication).setToken(response.body()!!.data.token)
                                        (activity!!.applicationContext as TravelMakerApplication).settingUserInfo(response.body()!!.data.checkResult[0])
                                        val intent = Intent(activity!!.applicationContext, MainActivity::class.java)
                                        activity!!.startActivity(intent)
                                        activity!!.finish()
                                    }
                            }
                        })
                    }
                else {
                    mView.nickname_edit.setText("")
                    mView.nickname_edit.hint = "닉네임이 중복됩니다."
                }
            }
        })
    }
}