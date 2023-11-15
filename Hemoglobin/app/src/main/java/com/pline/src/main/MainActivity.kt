package com.pline.src.main

import android.content.Intent
import android.os.Bundle
import com.pline.R
import com.pline.config.ApplicationClass.Companion.sSharedPreferences
import com.pline.config.BaseActivity
import com.pline.databinding.ActivityMainBinding
import com.pline.src.main.home.HomeFragment
import com.pline.src.main.info.InfoFragment
import com.pline.src.main.map.MapFragment
import com.pline.src.main.myPage.MyPageFragment
import com.pline.src.main.register.LoginActivity

/**
 * Activities that contain bottom navigation tabs
 */
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // When already signed up and logged in
        /*if(sSharedPreferences.getString("jwt","") == "") {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }*/
        sSharedPreferences.edit().putString("jwt","eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWQiOjEsImlhdCI6MTcwMDAzNjIzNiwiZXhwIjoxNzYzMTA4MjM3fQ.-0DzDtYbcQQWHVHrMoWy1YPSzLTSTLpeyQOWYIIze8Q").apply()
        sSharedPreferences.edit().putInt("memberId",1).apply()
        // Set default bottom navigation item
        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()
        // Switch fragments when click on each tab
        binding.mainBtmNav.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_main_btm_nav_home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, HomeFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_main_btm_nav_map -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, MapFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_main_btm_nav_info -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, InfoFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_main_btm_nav_my_page -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, MyPageFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            selectedItemId = R.id.menu_main_btm_nav_home
        }
    }
}