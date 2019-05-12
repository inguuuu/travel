package com.teamandroid.travelmaker.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import com.teamandroid.travelmaker.NaverUserInfo
import com.teamandroid.travelmaker.R
import com.teamandroid.travelmaker.TravelMakerApplication
import com.teamandroid.travelmaker.UserInfo
import com.teamandroid.travelmaker.get.GetNaverLoginResponse
import com.teamandroid.travelmaker.help.HelpActivity
import com.teamandroid.travelmaker.main.MainActivity
import com.teamandroid.travelmaker.post.PostSignIn
import com.teamandroid.travelmaker.post.PostSignup
import kotlinx.android.synthetic.main.fragment_login.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment :Fragment() {

    lateinit var mContext : Context
    lateinit var activity: LoginActivity
    lateinit var mOAuthClientModule : OAuthLogin

    companion object {
        val OAUTH_CLIENT_ID = "tAUdbi7BhPBDG0t6n1an"
        val OAUTH_CLIENT_SECRET = "QNKg4YJ5aZ"
        val OAUTH_CLIENT_NAME = "travelmaker"
        val NAVER_LOGIN_URL = "https://openapi.naver.com/"
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity = getActivity() as LoginActivity
        mContext = activity.applicationContext

        val view = inflater.inflate(R.layout.fragment_login, container, false)

        mOAuthClientModule = OAuthLogin.getInstance()
        mOAuthClientModule.init(mContext, OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET,OAUTH_CLIENT_NAME)


        val mOAuthLoginHandler = object : OAuthLoginHandler(){
            override fun run(success: Boolean) {
                if(success){
                    Log.d("UserProfile",mOAuthClientModule.getAccessToken(mContext))
                    requireUserProfile()
                }

                else{
                    val errorCode = mOAuthClientModule.getLastErrorCode(mContext).code
                    val errorDesc = mOAuthClientModule.getLastErrorDesc(mContext)
                    Log.e("NaverLoginError", "errorCode:" + errorCode  + ", errorDesc:" + errorDesc)
                }
            }
        }

        view.btn_naverLogin.setOAuthLoginHandler(mOAuthLoginHandler)
        view.guide.setOnClickListener{
            val intent = Intent(activity!!.applicationContext, HelpActivity::class.java)
            startActivity(intent)
        }
        return view
    }


    private fun requireUserProfile(){
        (mContext as TravelMakerApplication).makeNetworkService(NAVER_LOGIN_URL)
        val getUserProfile =  (mContext as TravelMakerApplication).getApplicationNetworkService().getUserProfile("Bearer "+mOAuthClientModule.getAccessToken(mContext))
        getUserProfile.enqueue(object : Callback<GetNaverLoginResponse> {
            override fun onFailure(call: Call<GetNaverLoginResponse>?, t: Throwable?) {
                Log.d("UserProfile","Error")
            }

            override fun onResponse(call: Call<GetNaverLoginResponse>?, response: Response<GetNaverLoginResponse>?) {
                if(response!!.isSuccessful){
                    val userProfile = response.body()!!.response

                    (mContext as TravelMakerApplication).makeNetworkService("http://13.209.235.23:2345/")

                    if(userProfile.age.compareTo("10-19") == 0){
                        userProfile.age = "10"
                    }else if(userProfile.age.compareTo("20-29") == 0){
                        userProfile.age = "20"
                    }else if(userProfile.age.compareTo("30-39") == 0){
                        userProfile.age = "30"
                    }else if(userProfile.age.compareTo("40-49") == 0){
                        userProfile.age = "40"
                    }else if(userProfile.age.compareTo("50-59") == 0){
                        userProfile.age = "50"
                    }else if(userProfile.age.compareTo("60-69") == 0){
                        userProfile.age = "60"
                    }

                    Log.d("id",userProfile.id)
                    val postSignIn = (mContext as TravelMakerApplication).getApplicationNetworkService().postUserSignIn(userProfile.id)

                    postSignIn.enqueue(object : Callback<PostSignIn>{
                        override fun onFailure(call: Call<PostSignIn>?, t: Throwable?) {
                            t!!.printStackTrace()
                        }

                        override fun onResponse(call: Call<PostSignIn>?, response: Response<PostSignIn>?) {
                            if(response!!.isSuccessful){
                                    Log.d("success","success")
                                    (mContext as TravelMakerApplication).setToken(response.body()!!.data.token)
                                    (mContext as TravelMakerApplication).settingUserInfo(response.body()!!.data.checkResult[0])
                                    val intent = Intent(activity.applicationContext,MainActivity::class.java)
                                    activity.startActivity(intent)
                                    activity.finish()
                            }
                            else{
                                activity.changeFragment(MakeNickNameFragment.newInstance(NaverUserInfo(
                                        userProfile.id,
                                        userProfile.name,
                                        userProfile.age,
                                        userProfile.gender,
                                        "",
                                        userProfile.email,
                                        "",
                                        userProfile.profile_image
                                )))
                            }
                        }
                    })
                }
            }
        })
    }
}