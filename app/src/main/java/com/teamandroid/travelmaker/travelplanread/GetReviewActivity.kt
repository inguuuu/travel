package com.teamandroid.travelmaker.travelplanread

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.teamandroid.travelmaker.R
import kotlinx.android.synthetic.main.activity_review_get.*

class GetReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_get)
        review_finish_btn.setOnClickListener {
            finish()
        }
    }
}
