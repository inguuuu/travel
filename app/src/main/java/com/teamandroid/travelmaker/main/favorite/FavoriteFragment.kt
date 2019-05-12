package com.teamandroid.travelmaker.main.favorite

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamandroid.travelmaker.*
import com.teamandroid.travelmaker.main.Category
import com.teamandroid.travelmaker.main.Country
import com.teamandroid.travelmaker.main.MainActivity
import com.teamandroid.travelmaker.detail.DetailActivity
import com.teamandroid.travelmaker.post.PostBookMark
import kotlinx.android.synthetic.main.fragment_favorite.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteFragment : Fragment() {

    lateinit var country : ArrayList<Country>
    lateinit var experts : ArrayList<Expert>
    lateinit var mView : View
    companion object {
        fun newInstance(categories : ArrayList<Category>): FavoriteFragment {
            val fragment = FavoriteFragment()
            val temp  = ArrayList<Country>()

            for(i in 0..(categories.size - 1 )){
                for(j in 0..(categories[i].country.size -1 )){
                        temp.add(categories[i].country[j])
                }
            }
            fragment.country = temp
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_favorite,container,false)

        (activity as MainActivity).initActivityDesign()

        requestBookMark()

        return mView
    }

    fun requestBookMark(){
        val postBookMark = (activity!!.applicationContext as TravelMakerApplication).getApplicationNetworkService().postBookMark(
                (activity!!.applicationContext as TravelMakerApplication).userToken
        )

        postBookMark.enqueue(object : Callback<PostBookMark>{
            override fun onFailure(call: Call<PostBookMark>?, t: Throwable?) {
                t!!.printStackTrace()
            }

            override fun onResponse(call: Call<PostBookMark>?, response: Response<PostBookMark>?) {
                if(response!!.isSuccessful){
                    experts = response.body()!!.expert
                    Log.d("experts",experts.size.toString())
                    val index = response.body()!!.country
                    var temp  = ArrayList<Country>()
                    var flag = false
                        for(i in 0..(index.size -1)){
                            if(index[i].country_idx == 8){
                                flag = true
                            }
                        }

                    if(!flag){
                        for(i in 0..(country.size -1 )){
                            if(country[i].countryData.country_idx != 8){
                                temp.add(country[i])
                            }
                        }
                    }
                    else{
                        temp = country
                    }
                    mView.recycler_country.adapter = CountryRecyclerViewAdapter(temp)
                    mView.recycler_country.layoutManager = LinearLayoutManager(activity!!.applicationContext,LinearLayoutManager.HORIZONTAL,false)
                    mView.recycler_country.addOnItemTouchListener(RecyclerItemClickListener(activity!!.applicationContext, mView.recycler_country,
                            object : RecyclerItemClickListener.OnItemClickListener{
                                override fun onItemClick(view: View, position: Int) {
                                    val intent = Intent(activity!!.applicationContext, DetailActivity::class.java)
                                    intent.putExtra("countryData",country[position].countryData)
                                    startActivity(intent)
                                }

                                override fun onLongItemClick(view: View, position: Int) {}
                            }))


                    mView.recycler_expert.adapter = PersonRecyclerViewAdapter(experts)
                    mView.recycler_expert.layoutManager = LinearLayoutManager(activity!!.applicationContext)
                    mView.recycler_expert.addOnItemTouchListener(RecyclerItemClickListener(activity!!.applicationContext, mView.recycler_expert,
                            object : RecyclerItemClickListener.OnItemClickListener{
                                override fun onItemClick(view: View, position: Int) {
                                    val intent = Intent(activity!!.applicationContext, ExpertActivity::class.java)
                                    intent.putExtra("expert_idx",experts[position].user_idx)
                                    intent.putExtra("expert_grade",experts[position].expert_grade)
                                    intent.putExtra("country_idx",8)
                                    startActivity(intent)
                                }

                                override fun onLongItemClick(view: View, position: Int) {

                                }
                            }))
                }
            }

        })
    }
}