package com.teamandroid.travelmaker

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import com.bumptech.glide.Glide
import com.teamandroid.travelmaker.travelplanwrite.Specific.SpecificActivity
import com.teamandroid.travelmaker.travelplanwrite.dataSets.Cost
import com.teamandroid.travelmaker.travelplanwrite.dataSets.Data
import com.teamandroid.travelmaker.travelplanwrite.dataSets.TotalData


import kotlinx.android.synthetic.main.activity_cost.*
class CostActivity : AppCompatActivity(), View.OnClickListener {
    private val COST_REQUEST = 101
    lateinit var commentEditText: EditText
    lateinit var budgetEditText: EditText

    lateinit var cost_comment :String
    lateinit var cost_budget :String

    var curDay :Int = 0
    var position : Int = 0

    override fun onClick(v: View?) {
        when(v){
            confirm_cost_btn->{
                commentEditText = findViewById(R.id.editText_budget_comment)
                budgetEditText = findViewById(R.id.editText_budget)
                cost_comment = commentEditText.text.toString()
                cost_budget = budgetEditText.text.toString()
                var arrayList: ArrayList<Data> = TotalData.totalData.get(curDay)!!
                var tempCost: Cost = Cost(cost_comment, cost_budget)
                arrayList.get(position).cost = tempCost
                TotalData.totalData.put(curDay, arrayList)
                Log.d("확인", cost_comment)
                Log.d("확인", cost_budget)
                Log.d("확인", "돈들어옴!!!!")
                intent = Intent(this, SpecificActivity::class.java)
                intent.putExtra("cost",curDay)
                startActivity(intent)
                finish()

//                val returnIntent = Intent()
//                returnIntent.putExtra("cost_return", "cost_return")
//                setResult(Activity.RESULT_OK, returnIntent)
//                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cost)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.parseColor("#14DAC6")
        }

        confirm_cost_btn.setOnClickListener(this)
        if(intent.hasExtra("cost")){
            curDay = intent.getIntegerArrayListExtra("cost")[0]
            position = intent.getIntegerArrayListExtra("cost")[1]

        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (requestCode == COST_REQUEST) {
//            if (resultCode == Activity.RESULT_OK) {
//                        try {
//                            var commentEditText: EditText = findViewById(R.id.editText_budget_comment)
//                            var budgetEditText: EditText = findViewById(R.id.editText_budget)
//
//                            var cost_comment = commentEditText.text.toString()
//                            var cost_budget = budgetEditText.text.toString()
//
//
//                            var curDay = intent.getIntegerArrayListExtra("cost")[0]
//                            var position = intent.getIntegerArrayListExtra("cost")[1]
//
//                            var arrayList: ArrayList<Data> = TotalData.totalData.get(curDay)!!
//                            var tempCost: Cost = Cost(cost_comment, cost_budget)
//                            arrayList.get(position).cost = tempCost
//                            TotalData.totalData.put(curDay, arrayList)
//                            Log.d("확인확인", "돈들어옴!!!!")
//
//                        } catch (e: Exception) {
//                            e.printStackTrace()
//                    }
//                }
//            }
//        }
    }
