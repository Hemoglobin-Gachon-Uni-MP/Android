package com.pline

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.pline.src.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InfoFragmentTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    // Before testing, click my page tab
    @Before
    fun tapMyPage() {
        onView(withId(R.id.menu_main_btm_nav_info)).perform(click())
    }

    // Test 1
    @Test
    fun clickConditionsLayoutShowInfoDesc() {
        // When:  A user click conditions layout
        onView(withId(R.id.fragment_info_cl_conditions)).perform(click())

        // Then: Show conditions description webview
        onView(withId(R.id.fragment_info_wv_conditions_desc))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}