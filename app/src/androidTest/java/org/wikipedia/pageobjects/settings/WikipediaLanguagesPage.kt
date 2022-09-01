package org.wikipedia.pageobjects.settings

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.allOf
import org.wikipedia.R

class WikipediaLanguagesPage {

    private val addLanguageButton = "ADD LANGUAGE"
    private val itemTitle = R.id.wiki_language_title
    private val moreMenuButton = "More options"
    private val removeLanguageButton = "Remove language"
    private val deleteButton = R.id.menu_delete_selected
    private val okButton = "OK"

    fun getLanguageLabel(language: String) = onView(allOf(withId(itemTitle), withText(language)))

    fun clickAddLanguageButton() {
        onView(allOf(withId(itemTitle), withText(addLanguageButton))).perform(click())
    }

    fun removeLanguage(language: String) {
        onView(withContentDescription(moreMenuButton)).perform(click())
        onView(withText(removeLanguageButton)).perform(click())
        getLanguageLabel(language).perform(click())
        onView(withId(deleteButton)).perform(click())
        onView(withText(okButton)).perform(click())
    }
}