package com.teamandroid.travelmaker.detail

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.teamandroid.travelmaker.*
import com.teamandroid.travelmaker.main.Country
import com.teamandroid.travelmaker.post.PostCountryBookMark
import com.teamandroid.travelmaker.review.ApplyReview
import com.teamandroid.travelmaker.review.CommentRecyclerAdapter
import kotlinx.android.synthetic.main.apply_layout.view.*
import kotlinx.android.synthetic.main.fragment_countrydetail.*
import kotlinx.android.synthetic.main.fragment_countrydetail.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.math.exp

class CountryDetailFragment : Fragment(), View.OnClickListener {
    lateinit var mview : View
    lateinit var country : Country
    lateinit var applications : ArrayList<Application>
    lateinit var experts : ArrayList<Expert>
    lateinit var sharedPreferences : SharedPreferences
    var expertCount = 0

    companion object {
        fun newInstance(country : Country, experts: ArrayList<Expert>, applications : ArrayList<Application>): CountryDetailFragment {
            val fragment = CountryDetailFragment()
            fragment.country = country

            fragment.experts = experts
            fragment.applications = applications
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mview = inflater.inflate(R.layout.fragment_countrydetail,container,false)
        (activity as DetailActivity).countryDetailDesign()
        mview.btn_moreApplication.setOnClickListener(this)
        mview.btn_moreExpert.setOnClickListener(this)
        mview.country_favorite.setOnClickListener(this)
        mview.countryDetail_image.setImageBitmap(country.detailBitmap)


        if(country.countryData.country_idx != 8){
            mview.china_box.visibility = View.INVISIBLE
            mview.country_box.visibility = View.VISIBLE

            var img = 0
            when(country.countryData.country_idx){
                10 -> img = R.drawable.korea_page
                12 -> img = R.drawable.japan_page
                13 -> img = R.drawable.uk_page
                14 -> img = R.drawable.france_page
                15 -> img = R.drawable.spain_page
                16 -> img = R.drawable.canada_page
                17 -> img = R.drawable.usa_page
                18 -> img = R.drawable.mexico_page
            }
            mview.country_box.setBackgroundResource(img)
        }
        else {
            mview.china_box.visibility = View.VISIBLE
            mview.country_box.visibility = View.INVISIBLE

            sharedPreferences = activity!!.getSharedPreferences("favorite",MODE_PRIVATE)
            mview.country_favorite.isSelected = sharedPreferences.getBoolean(country.countryData.country_idx.toString(),false)
            mview.weather.text = country.countryData.country_climate.toString() + "℃"
            mview.money.text = "약"+country.countryData.country_exchange.toString()+"위안"
            mview.time.text = "서울-"+country.countryData.country_time_difference+"시간"
            mview.language.text = country.countryData.country_language

            mview.expert_image1.setOnClickListener(this)
            mview.expert_image2.setOnClickListener(this)
            mview.expert_image3.setOnClickListener(this)

            settingExpert(mview.expert_image1, mview.expert_grade1, mview.expert_nickName1, mview.expert_tendency1, mview.expert_city1
                    , mview.expert_ratingBar1, mview.expert_ratingValue1)

            settingExpert(mview.expert_image2, mview.expert_grade2, mview.expert_nickName2, mview.expert_tendency2, mview.expert_city2
                    , mview.expert_ratingBar2, mview.expert_ratingValue2)

            settingExpert(mview.expert_image3, mview.expert_grade3, mview.expert_nickName3, mview.expert_tendency3, mview.expert_city3
                    , mview.expert_ratingBar3, mview.expert_ratingValue3)

            val _applications = ArrayList<Application>()

            if (applications.size < 5) {
                for (i in 0..(applications.size - 1)) {
                    _applications.add(applications[i])
                }
            } else {
                for (i in 0..4) {
                    _applications.add(applications[i])
                }
            }

            mview.application_recycler.adapter = MoreApplicationsRecyclerAdapter(_applications)
            mview.application_recycler.layoutManager = LinearLayoutManager(activity!!.applicationContext)

            mview.application_recycler.addOnItemTouchListener(RecyclerItemClickListener(activity!!.applicationContext, mview.application_recycler,
                    object : RecyclerItemClickListener.OnItemClickListener{
                        override fun onItemClick(view: View, position: Int) {
                            val intent = Intent(activity!!.applicationContext, ApplyReview::class.java)
                            intent.putExtra("board_idx",applications[position].board_idx)
                            intent.putExtra("position",position)
                            startActivityForResult(intent,200)
                        }

                        override fun onLongItemClick(view: View, position: Int) {
                        }
                    }))

        }
        return mview
    }

    override fun onClick(v: View?) {
        if(v == mview.btn_moreApplication){
            (activity as DetailActivity).changeFragment(MoreApplicationsFragment.newInstance(applications))
        }
        else if(v == mview.btn_moreExpert){
            (activity as DetailActivity).changeFragment(MoreExpertsFragment.newInstance(experts))
        }
        else if(v == mview.country_favorite){
            mview.country_favorite.isSelected = !mview.country_favorite.isSelected

            val editor = sharedPreferences.edit()
            editor.putBoolean(country.countryData.country_idx.toString(),mview.country_favorite.isSelected).apply()

            val postCountryBookMark = (activity!!.applicationContext as TravelMakerApplication).getApplicationNetworkService().postCountryBookMark(
                    (activity!!.applicationContext as TravelMakerApplication).userToken,country.countryData.country_idx)

            postCountryBookMark.enqueue(object : Callback<PostCountryBookMark>{
                override fun onFailure(call: Call<PostCountryBookMark>?, t: Throwable?) {

                }

                override fun onResponse(call: Call<PostCountryBookMark>?, response: Response<PostCountryBookMark>?) {
                }

            })
        }

        else{
            val intent = Intent(activity!!.applicationContext,ExpertActivity::class.java)

            if(v == mview.expert_image1){
                intent.putExtra("expert_idx",experts[0].user_idx)
                intent.putExtra("expert_grade", experts[0].expert_grade)
            }
            else if(v == mview.expert_image2){
                intent.putExtra("expert_idx",experts[1].user_idx)
                intent.putExtra("expert_grade", experts[1].expert_grade)
            }else {
                intent.putExtra("expert_idx",experts[2].user_idx)
                intent.putExtra("expert_grade", experts[2].expert_grade)
            }

            intent.putExtra("country_idx",country.countryData.country_idx)

            startActivity(intent)
        }

    }

    private fun settingExpert(expert_image : ImageView, expert_grade : ImageView, expert_nickName : TextView, expert_tendency : TextView, expert_city : TextView,
                              expert_ratingBar : RatingBar, expert_ratingValue : TextView){

        when(expertCount){
            0 -> expert_image.setImageResource(R.drawable.china_expert_img)
            1 -> expert_image.setImageResource(R.drawable.expert_img_1)
            2 -> expert_image.setImageResource(R.drawable.expert_img_2)
        }


        var city = ""

        if(experts[expertCount].expert_city1 != null){
            val token = StringTokenizer(experts[expertCount].expert_city1, " ")
            var string = token.nextToken()
            Log.d("string",string)
            if(string.compareTo(country.countryData.country_name) == 0){
                if(token.hasMoreTokens()){
                    string = token.nextToken()
                    city = "" + string + " "
                }
            }
        }

        if(experts[expertCount].expert_city2 != null){
            val token = StringTokenizer(experts[expertCount].expert_city2, " ")
            var string = token.nextToken()

            if(string.compareTo(country.countryData.country_name) == 0){
                if(token.hasMoreTokens()){
                    string = token.nextToken()
                    city = "" + string + " "
                }
            }
        }

        if(experts[expertCount].expert_city3 != null){
            val token = StringTokenizer(experts[expertCount].expert_city3, " ")
            var string = token.nextToken()

            if(string.compareTo(country.countryData.country_name) == 0){
                if(token.hasMoreTokens()){
                    string = token.nextToken()
                    city = "" + string + " "
                }
            }
        }

        expert_city.text = city
        var img = 0
        when(experts[expertCount].expert_grade){
            0 -> img = R.drawable.blue_crown_big
            1 -> img = R.drawable.emerald_crown
            2 -> img = R.drawable.gold_crown_big
        }

        expert_grade.setImageResource(img)

        expert_nickName.text = experts[expertCount].user_nick

        var style = ""
        when(experts[expertCount].user_style){
            0 -> style = "콜럼버스형"
            1 -> style = "인생샷형"
            2 -> style = "액티비티형"
            3 -> style = "먹고죽자형"
            4 -> style = "느긋한 휴양자형"
            5 -> style = "자린고비형"
            6 -> style = "쇼핑형"
        }

        expert_tendency.text = style

            expert_ratingBar.rating = (experts[expertCount].expert_rate)!!.toFloat()
            expert_ratingValue.text = "("+experts[expertCount].expert_rate.toString()+")"
        expertCount++
    }

    fun changeCommentCount(position : Int){
        applications[position].comment_count++
        (mview.application_recycler.adapter as MoreApplicationsRecyclerAdapter).addItems(applications)
    }
}