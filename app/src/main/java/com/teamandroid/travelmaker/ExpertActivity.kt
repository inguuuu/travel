package com.teamandroid.travelmaker

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.teamandroid.travelmaker.write.ApplyWrite
import kotlinx.android.synthetic.main.activity_expert.*
import kotlin.math.exp

class ExpertActivity : AppCompatActivity(), View.OnClickListener {
    var expert_idx = -1
    var expert_grade = -1
    var country_idx = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expert)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.statusbarWhite, null)
        }

        expert_idx = intent.getIntExtra("expert_idx", -1)
        expert_grade = intent.getIntExtra("expert_grade", -1)
        country_idx = intent.getIntExtra("country_idx",8)

        btn_close.setOnClickListener(this)
        gotoApply.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v == gotoApply){
            val intent = Intent(this, ApplyWrite::class.java)
            intent.putExtra("country_idx",country_idx)
            intent.putExtra("expert_idx", expert_idx)
            intent.putExtra("expert_grade", expert_grade)

            startActivity(intent)
            finish()
        }

        else if(v == btn_close){
            onBackPressed()
        }
    }

}
