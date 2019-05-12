package com.teamandroid.travelmaker.login

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.WindowManager
import com.teamandroid.travelmaker.R
import kotlinx.android.synthetic.main.activity_detail.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.statusbarWhite, null)
        }
        supportFragmentManager.beginTransaction().replace(R.id.login_container,LoginFragment()).commit()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 0){
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.statusBarColor = resources.getColor(R.color.statusbarWhite, null)
            }

            supportFragmentManager.popBackStack()
        }
        else{
            super.onBackPressed()
        }
    }

    fun changeFragment(fragment : Fragment){
        if(fragment is LoginFragment){
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.statusBarColor = resources.getColor(R.color.statusbarWhite, null)
            }
        }
        else{
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.statusBarColor = resources.getColor(R.color.statusbarWhite, null)
            }
        }
        supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.login_container,fragment).commit()
    }
}
