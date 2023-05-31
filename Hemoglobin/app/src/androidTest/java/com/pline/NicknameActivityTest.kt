package com.pline

import android.widget.EditText
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.pline.src.main.register.RegisterNicknameActivity
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test

// Nickname 8 character limit test
class NicknameActivityTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(RegisterNicknameActivity::class.java)
    @Test
    fun checkNicknameLength() {
        // Enter text in editText.
        onView(withId(R.id.activity_register_nickname_et))
            .perform(typeText("NicknameOverEightCharacters"))
        closeSoftKeyboard()
        // click btn_next
        onView(withId(R.id.btn_next)).perform(click())

        // Check the length of editText's text.
        onView(withId(R.id.activity_register_nickname_et))
            .check { view, _ ->
                val editText = view as? EditText
                val length = editText?.text?.length ?: 0
                assertTrue("Nickname length is over 8 characters", length <= 8)
            }
    }
}