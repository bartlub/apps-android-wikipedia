package org.wikipedia.pageobjects.settings

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.wikipedia.R

class SettingsPage {

    private val itemsList = R.id.recycler_view
    private val wikipediaLanguagesButtonPosition = 1
    private val aboutWikipediaAppButtonPosition = 16
    private val termsOfUseButtonPosition = 19

    fun clickWikipediaLanguagesButton() {
        onView(withId(itemsList))
            .perform(actionOnItemAtPosition<ViewHolder>(wikipediaLanguagesButtonPosition, click()))
    }

    fun clickAboutWikipediaAppButton() {
        onView(withId(itemsList))
            .perform(actionOnItemAtPosition<ViewHolder>(aboutWikipediaAppButtonPosition, click()))
    }

    fun clickTermsOfUseButton() {
        onView(withId(itemsList))
            .perform(actionOnItemAtPosition<ViewHolder>(termsOfUseButtonPosition, click()))
    }
}