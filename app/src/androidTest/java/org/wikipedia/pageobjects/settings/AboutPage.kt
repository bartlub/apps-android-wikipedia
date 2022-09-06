package org.wikipedia.pageobjects.settings

import android.content.Intent.ACTION_SENDTO
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.wikipedia.R
import org.wikipedia.base.BasePage

class AboutPage: BasePage() {

    private val sendAppFeedbackButton = withId(R.id.send_feedback_text)

    fun doesSendAppFeedbackButtonTriggerIntent(): Boolean {
        clickElementInScrollView(sendAppFeedbackButton)
        return isIntentTriggered(ACTION_SENDTO)
    }
}