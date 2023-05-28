package com.pline

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.pline.src.main.MainActivity
import com.pline.src.main.utils.PostListRVAdapter
import org.junit.Before

import org.junit.Rule
import org.junit.Test

// 댓글 개수 잘 증가하는지 테스트
class NewPostingTest {
    @get:Rule
    public val activityRule = ActivityScenarioRule(MainActivity::class.java)

    // click the latest post
    @Before
    fun clickPlusBtn(){
        onView(withId(R.id.menu_main_btm_nav_home)).perform(click())
        // click plus btn
        onView(withId(R.id.home_btn_new_post_iv)).perform(click())
    }
    @Test
    fun receiverPostTest(){
        // click receiver new post
        onView(withId(R.id.home_new_post_for_receiver_tv)).perform(click())
        // check if the page is for receiver
        onView(withId(R.id.new_post_receiver_toolbar_tv)).check(matches(withText("수혈자 새 게시물")))

    }

    @Test
    fun providerPostTest(){
        // click provider new post
        onView(withId(R.id.home_new_post_for_provider_tv)).perform(click())
        // check if the page is for provider
        onView(withId(R.id.new_post_receiver_toolbar_tv)).check(matches(withText("공혈자 새 게시물")))

    }



}