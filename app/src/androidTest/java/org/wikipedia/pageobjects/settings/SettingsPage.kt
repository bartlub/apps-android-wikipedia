package org.wikipedia.pageobjects.settings

import android.content.Intent.ACTION_VIEW
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.wikipedia.R
import org.wikipedia.base.BasePage

class SettingsPage: BasePage() {

    private val itemsList = withId(R.id.recycler_view)
    private val wikipediaLanguagesButton = withText("Wikipedia languages")
    private val aboutWikipediaAppButton = withText("About the Wikipedia app")
    private val termsOfUseButton = withText("Terms of use")

    fun clickWikipediaLanguagesButton() {
        clickDescendantElementInRecyclerView(itemsList, wikipediaLanguagesButton)
    }

    fun clickAboutWikipediaAppButton() {
        clickDescendantElementInRecyclerView(itemsList, aboutWikipediaAppButton)
    }

    fun doesTermsOfUseButtonTriggerIntent(): Boolean {
        clickDescendantElementInRecyclerView(itemsList, termsOfUseButton)
        return isIntentTriggered(ACTION_VIEW)
    }
}