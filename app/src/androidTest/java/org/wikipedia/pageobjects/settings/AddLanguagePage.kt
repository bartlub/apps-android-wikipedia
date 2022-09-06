package org.wikipedia.pageobjects.settings

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.wikipedia.R
import org.wikipedia.base.BasePage

class AddLanguagePage: BasePage() {

    private val searchLanguageButton = withId(R.id.menu_search_language)
    private val searchTextField = withId(R.id.search_src_text)
    private val languagesList = withId(R.id.languages_list_recycler)
    private fun languageLabel(language: String): Matcher<View> {
        return allOf(withId(R.id.localized_language_name), withText(language))
    }

    fun searchAndSelectLanguage(language: String) {
        clickElement(searchLanguageButton)
        enterText(searchTextField, language)
        clickDescendantElementInRecyclerView(languagesList, languageLabel(language))
    }
}