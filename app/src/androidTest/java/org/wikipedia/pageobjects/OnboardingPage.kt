package org.wikipedia.pageobjects

import androidx.test.espresso.matcher.ViewMatchers.withId
import org.wikipedia.R
import org.wikipedia.base.BasePage

class OnboardingPage: BasePage() {

    private val skipButton = withId(R.id.fragment_onboarding_skip_button)

    fun clickSkipButton() {
        clickElement(skipButton)
    }
}