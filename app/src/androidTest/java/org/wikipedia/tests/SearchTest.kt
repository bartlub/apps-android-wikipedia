package org.wikipedia.tests

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.web.assertion.WebViewAssertions.webMatches
import androidx.test.espresso.web.webdriver.DriverAtoms.getText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.wikipedia.pageobjects.ActiveSearchPage
import org.wikipedia.pageobjects.ItemPage
import org.wikipedia.pageobjects.OnboardingPage
import org.wikipedia.pageobjects.SearchPage
import org.wikipedia.pageobjects.navigation.TabBar
import org.wikipedia.testsupport.SearchSupport

@RunWith(AndroidJUnit4::class)
class SearchTest: BaseTest() {

    private val onboardingPage = OnboardingPage()
    private val activeSearchPage = ActiveSearchPage()
    private val tabBar = TabBar()
    private val searchPage = SearchPage()
    private val itemPage = ItemPage()
    private val searchSupport = SearchSupport()

    private val searchPhrase = "Dog"

    @Before
    fun skipOnboarding() {
        onboardingPage.clickSkipButton()
    }

    @Test
    fun shouldBePossibleToSearchFromExploreScreen() {
        searchSupport.searchFromExploreScreen(searchPhrase)
        itemPage.getItemHeader().check(webMatches(getText(), containsString(searchPhrase)))
    }

    @Test
    fun shouldBePossibleToSearchFromSearchScreen() {
        tabBar.clickSearchButton()
        searchPage.clickSearchBar()
        activeSearchPage.searchAndSelectItem(searchPhrase)
        itemPage.getItemHeader().check(webMatches(getText(), containsString(searchPhrase)))
    }

    @Test
    fun shouldBePossibleToSelectItemFromSearchHistory() {
        searchSupport.searchFromExploreScreen(searchPhrase)
        pressBack()
        activeSearchPage.clearSearchTextField()
        activeSearchPage.selectItemFromSearchHistory(1)
        activeSearchPage.getSearchTextField().check(matches(withText(searchPhrase)))
    }

    @Test
    fun shouldBePossibleToDeleteSearchHistory() {
        searchSupport.searchFromExploreScreen(searchPhrase)
        pressBack()
        activeSearchPage.clearSearchTextField()
        activeSearchPage.deleteSearchHistory()
        activeSearchPage.getDeleteHistoryButton().check(matches(not(isDisplayed())))
    }

    @Test
    fun shouldBePossibleToSelectItemFromBrowsingHistory() {
        searchSupport.searchFromExploreScreen(searchPhrase)
        pressBack()
        closeSoftKeyboard()
        repeat(2) { pressBack() }
        tabBar.clickSearchButton()
        searchPage.selectItemFromBrowsingHistory(searchPhrase)
        itemPage.getItemHeader().check(webMatches(getText(), containsString(searchPhrase)))
    }

    @Test
    fun shouldBePossibleToDeleteBrowsingHistory() {
        searchSupport.searchFromExploreScreen(searchPhrase)
        pressBack()
        closeSoftKeyboard()
        repeat(2) { pressBack() }
        tabBar.clickSearchButton()
        searchPage.deleteBrowsingHistory()
        searchPage.getDeleteHistoryButton().check(matches(not(isDisplayed())))
    }
}