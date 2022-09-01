package org.wikipedia.tests

import android.content.Intent
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.wikipedia.main.MainActivity
import org.wikipedia.pageobjects.OnboardingPage
import org.wikipedia.pageobjects.navigation.MoreMenu
import org.wikipedia.pageobjects.navigation.TabBar
import org.wikipedia.pageobjects.settings.AboutPage
import org.wikipedia.pageobjects.settings.SettingsPage
import org.wikipedia.pageobjects.settings.WikipediaLanguagesPage
import org.wikipedia.testsupport.SettingsSupport

@RunWith(AndroidJUnit4::class)
class SettingsTest: BaseTest() {

    @Rule
    @JvmField
    val intentsTestRule = IntentsTestRule(MainActivity::class.java)

    private val onboardingPage = OnboardingPage()
    private val tabBar = TabBar()
    private val moreMenu = MoreMenu()
    private val settingsPage = SettingsPage()
    private val wikipediaLanguagesPage = WikipediaLanguagesPage()
    private val aboutPage = AboutPage()
    private val settingsSupport = SettingsSupport()

    private val language = "Polski"

    @Before
    fun goToSettings() {
        onboardingPage.clickSkipButton()
        tabBar.clickMoreButton()
        moreMenu.clickSettingsButton()
    }

    @Test
    fun shouldBePossibleToAddLanguage() {
        settingsSupport.addLanguage(language)
        wikipediaLanguagesPage.getLanguageLabel(language).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBePossibleToRemoveLanguage() {
        settingsSupport.addLanguage(language)
        wikipediaLanguagesPage.removeLanguage(language)
        wikipediaLanguagesPage.getLanguageLabel(language).check(doesNotExist())
    }

    @Test
    fun shouldExitAppOnRequestToSendAppFeedback() {
        settingsPage.clickAboutWikipediaAppButton()
        aboutPage.clickSendAppFeedbackButton()
        intended(hasAction(Intent.ACTION_SENDTO))
    }

    @Test
    fun shouldExitAppOnRequestToReadTermsOfUse() {
        settingsPage.clickTermsOfUseButton()
        intended(hasAction(Intent.ACTION_VIEW))
    }
}