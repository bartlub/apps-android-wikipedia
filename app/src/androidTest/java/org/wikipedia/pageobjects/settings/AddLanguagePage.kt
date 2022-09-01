package org.wikipedia.pageobjects.settings

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.wikipedia.R

class AddLanguagePage {

    private val searchLanguageButton = R.id.menu_search_language
    private val searchTextField = R.id.search_src_text
    private val languagesList = R.id.languages_list_recycler

    fun searchAndSelectLanguage(language: String) {
        onView(withId(searchLanguageButton)).perform(click())
        onView(withId(searchTextField)).perform(typeText(language))
        onView(withId(languagesList))
            .perform(actionOnItemAtPosition<ViewHolder>(1, click()))
    }
}