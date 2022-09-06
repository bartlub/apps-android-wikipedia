package org.wikipedia.pageobjects.settings

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.wikipedia.R
import org.wikipedia.base.BasePage

class WikipediaLanguagesPage: BasePage() {

    private val itemTitleId = R.id.wiki_language_title
    private val addLanguageButton = allOf(withId(itemTitleId), withText("ADD LANGUAGE"))
    private val moreMenuButton = withContentDescription("More options")
    private val removeLanguageButton = withText("Remove language")
    private val deleteButton = withId(R.id.menu_delete_selected)
    private val okButton = withText("OK")
    private fun languageLabel(language: String): Matcher<View> {
        return allOf(withId(itemTitleId), withText(language))
    }

    fun clickAddLanguageButton() {
        clickElement(addLanguageButton)
    }

    fun removeLanguage(language: String) {
        clickElement(moreMenuButton)
        clickElement(removeLanguageButton)
        clickElement(languageLabel(language))
        clickElement(deleteButton)
        clickElement(okButton)
    }

    fun isLanguageLabelDisplayed(language: String): Boolean {
        return isDisplayed(languageLabel(language))
    }

    fun doesLanguageLabelNotExist(language: String): Boolean {
        return doesNotExist(languageLabel(language))
    }
}