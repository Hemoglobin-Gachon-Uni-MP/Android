package com.pline.src.main

import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import com.kakao.sdk.common.util.Utility
import com.pline.R
import com.pline.config.BaseActivity
import com.pline.databinding.ActivityMainBinding
import com.pline.src.main.home.HomeFragment
import com.pline.src.main.info.InfoFragment
import com.pline.src.main.map.MapFragment
import com.pline.src.main.myPage.MyPageFragment
import java.security.MessageDigest


class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()

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