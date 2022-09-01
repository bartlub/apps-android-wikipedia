package org.wikipedia.pageobjects.settings

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.wikipedia.R

class AboutPage {

    private val sendAppFeedbackButton = R.id.send_feedback_text

    fun clickSendAppFeedbackButton() {
        onView(withId(sendAppFeedbackButton)).perform(scrollTo(), click())
    }
}