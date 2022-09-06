package org.wikipedia.tests

import android.app.Activity.RESULT_OK
import android.app.Instrumentation.ActivityResult
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.isInternal
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.wikipedia.base.BaseTest
import org.wikipedia.pageobjects.OnboardingPage
import org.wikipedia.pageobjects.navigation.MoreMenu
import org.wikipedia.pageobjects.navigation.TabBar
import org.wikipedia.pageobjects.settings.AboutPage
import org.wikipedia.pageobjects.settings.SettingsPage
import org.wikipedia.pageobjects.settings.WikipediaLanguagesPage
import org.wikipedia.testsupport.SettingsSupport

@RunWith(AndroidJUnit4::class)
class SettingsTest: BaseTest() {

    private val onboardingPage = OnboardingPage()
    private val tabBar = TabBar()
    private val moreMenu = MoreMenu()
    private val settingsPage = SettingsPage()
    private val wikipediaLanguagesPage = WikipediaLanguagesPage()
    private val aboutPage = AboutPage()
    private val settingsSupport = SettingsSupport()

    private val language = "Polski"

    @Before
    fun setUpIntents() {
        Intents.init()
        intending(not(isInternal())).respondWith(ActivityResult(RESULT_OK, null))
    }

    @Before
    fun goToSettings() {
        onboardingPage.clickSkipButton()
        tabBar.clickMoreButton()
        moreMenu.clickSettingsButton()
    }

    @After
    fun releaseIntents() {
        Intents.release()
    }

    @Test
    fun shouldBePossibleToAddLanguage() {
        settingsSupport.addLanguage(language)
        assertTrue("'$language' language label is not displayed",
            wikipediaLanguagesPage.isLanguageLabelDisplayed(language))
    }

    @Test
    fun shouldBePossibleToRemoveLanguage() {
        settingsSupport.addLanguage(language)
        wikipediaLanguagesPage.removeLanguage(language)
        assertTrue("'$language' language label exists",
            wikipediaLanguagesPage.doesLanguageLabelNotExist(language))
    }

    @Test
    fun shouldExitAppOnRequestToSendAppFeedback() {
        settingsPage.clickAboutWikipediaAppButton()
        assertTrue("Intent has not been triggered",
            aboutPage.doesSendAppFeedbackButtonTriggerIntent())
    }

    @Test
    fun shouldExitAppOnRequestToReadTermsOfUse() {
        assertTrue("Intent has not been triggered",
            settingsPage.doesTermsOfUseButtonTriggerIntent())
    }
}