package com.pline

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.pline.src.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

// UI Test of my page fragment
class MyPageFragmentTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    // Before testing, click my page tab
    @Before
    fun tapMyPage() {
        onView(withId(R.id.menu_main_btm_nav_my_page)).perform(click())
    }

    // Test 1
    @Test
    fun clickDeleteAccountImbBtnShowDeleteDialog() {
        // When:  A user click delete my account button
        onView(withId(R.id.imgbtn_delete_account)).perform(click())

        // Then: Show delete dialog
        onView(withId(R.id.dialog_delete_my_account)).check(matches(isDisplayed()))
    }
}