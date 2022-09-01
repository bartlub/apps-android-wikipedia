package org.wikipedia.pageobjects

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.wikipedia.R

class OnboardingPage {

    private val skipButton = R.id.fragment_onboarding_skip_button

    fun clickSkipButton() {
        onView((withId(skipButton))).perform(click())
    }
}