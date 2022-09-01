package org.wikipedia.testsupport

import org.wikipedia.pageobjects.settings.AddLanguagePage
import org.wikipedia.pageobjects.settings.SettingsPage
import org.wikipedia.pageobjects.settings.WikipediaLanguagesPage

class SettingsSupport {

    private val settingsPage = SettingsPage()
    private val wikipediaLanguagesPage = WikipediaLanguagesPage()
    private val addLanguagesPage = AddLanguagePage()

    fun addLanguage(language: String) {
        settingsPage.clickWikipediaLanguagesButton()
        wikipediaLanguagesPage.clickAddLanguageButton()
        addLanguagesPage.searchAndSelectLanguage(language)
    }
}