package com.teamandroid.travelmaker.detail

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.teamandroid.travelmaker.*
import com.teamandroid.travelmaker.get.GetCountryDetail
import com.teamandroid.travelmaker.main.Country
import com.teamandroid.travelmaker.main.CountryData
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity(), View.OnClickListener{

    lateinit var country : Country
    lateinit var stackFragment : ArrayList<Fragment>

    lateinit var applications : ArrayList<Application>
    lateinit var experts : ArrayList<Expert>
    var isBackPress = false
    lateinit var countryData : CountryData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        countryData = intent.getParcelableExtra<CountryData>("countryData")

        btn_close.setOnClickListener(this)

        if(countryData.country_idx == 8)
            requestCountryDetail(countryData.country_idx)

        else {
            countryName.text = countryData.country_name
            country = Country(countryData,null,null)
            experts = ArrayList()
            applications = ArrayList()
            val fragment = CountryDetailFragment.newInstance(country,experts, applications)
            supportFragmentManager.beginTransaction().replace(R.id.other_container,fragment).commit()
        }

        stackFragment = ArrayList()
    }

    override fun onStart() {
        super.onStart()
    }

    fun changeFragment(fragment: Fragment){
        val manager = supportFragmentManager
        var container : Int

        if(main_container.visibility == View.VISIBLE){
            container = R.id.main_container
        }
        else{
            container = R.id.other_container
        }

        val currentFragment = manager.findFragmentById(container)

        if(fragment is CountryDetailFragment){
            countryDetailDesign()
            container = R.id.other_container
            main_container.visibility = View.INVISIBLE
            other_container.visibility = View.VISIBLE
        }

        else{
            otherDesign()
            container = R.id.main_container
            main_container.visibility = View.VISIBLE
            other_container.visibility = View.INVISIBLE
        }

        if(!isBackPress) {
            stackFragment.add(currentFragment)
            manager.beginTransaction().addToBackStack(null).replace(container, fragment).commit()
        }
        else{
            stackFragment.removeAt(stackFragment.size - 1)
        }

        isBackPress = false
    }

    override fun onClick(v: View?) {
        if(v == btn_close){
            finish()
        }
    }

    override fun onBackPressed(){

        if (stackFragment.size > 0) {
            isBackPress = true
            changeFragment(stackFragment[stackFragment.size - 1])
            supportFragmentManager.popBackStack()

        } else {
            super.onBackPressed()
        }
    }

    fun countryDetailDesign(){
        detail_toolBar.background = null
        detail_toolBar.setBackgroundColor(Color.parseColor("#20000000"))
        countryName.setTextColor(Color.parseColor("#F1F6FD"))
        detail_toolBar.setPadding(0,getStatusBarHeight(),0,0)
        btn_close.setImageResource(R.drawable.x_icon_white)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            window.decorView.systemUiVisibility = 0
        }
    }

    fun otherDesign(){
        detail_toolBar.background = ContextCompat.getDrawable(this,R.drawable.toolbar_background_normal)
        detail_toolBar.setBackgroundColor(Color.TRANSPARENT)
        countryName.setTextColor(Color.parseColor("#049DED"))
        detail_toolBar.setPadding(0,0,0,0)
        btn_close.setImageResource(R.drawable.x_icon_blue)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.statusbarWhite, null)
        }
    }

    private fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0)
            result = resources.getDimensionPixelSize(resourceId)

        return result
    }


    fun requestCountryDetail(country_idx : Int){
        val getCountryDetail  = (applicationContext as TravelMakerApplication).getApplicationNetworkService().getCountryDetail(country_idx)

        getCountryDetail.enqueue(object : Callback<GetCountryDetail>{
            override fun onFailure(call: Call<GetCountryDetail>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<GetCountryDetail>?, response: Response<GetCountryDetail>?) {
                if(response!!.isSuccessful){
                    country = Country(response.body()!!.country_info[0],null,null)
                    experts = response.body()!!.expert_info
                    applications = response.body()!!.board_info

                    var img = 0

                    when(country.countryData.country_idx){
                        10 -> img = R.drawable.korea_detail_img
                        8 -> img = R.drawable.china_detail_img
                        12 -> img = R.drawable.japan_detail_img
                        13 -> img = R.drawable.uk_detail_img
                        14 -> img = R.drawable.france_detail_img
                        15 -> img = R.drawable.spain_detail_img
                        16 -> img = R.drawable.canada_detail_img
                        17 -> img = R.drawable.usa_detail_img
                        18 -> img = R.drawable.mexico_detail_img
                    }

                    country.detailBitmap = BitmapFactory.decodeResource(resources, img)
                    countryName.text = country.countryData.country_name

                    val fragment = CountryDetailFragment.newInstance(country,experts, applications)
                    supportFragmentManager.beginTransaction().replace(R.id.other_container,fragment).commit()
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 200 && resultCode == RESULT_OK){
            val fragment = supportFragmentManager.findFragmentById(R.id.other_container) as CountryDetailFragment
            fragment.changeCommentCount(intent.getIntExtra("position",-1))
        }
    }
}
