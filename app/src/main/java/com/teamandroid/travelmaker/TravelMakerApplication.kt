package com.teamandroid.travelmaker

import android.app.Application
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TravelMakerApplication : Application(){
    lateinit var networkService: NetworkService
    lateinit var userInfo: UserInfo
    lateinit var userToken : String
    override fun onCreate() {
        super.onCreate()
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    fun makeNetworkService(baseUrl : String) : Unit{
        var retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        networkService = retrofit.create(NetworkService::class.java)
    }

    fun getApplicationNetworkService(): NetworkService {
        return networkService
    }

    fun setToken(userToken : String){
        this.userToken = userToken
    }

    fun settingUserInfo(userInfo : UserInfo){
        this.userInfo = userInfo
    }
}