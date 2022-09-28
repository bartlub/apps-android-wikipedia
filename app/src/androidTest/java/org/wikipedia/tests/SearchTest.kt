package org.wikipedia.tests

import androidx.test.espresso.Espresso.pressBack
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.wikipedia.base.BaseTest
import org.wikipedia.pageobjects.ActiveSearchPage
import org.wikipedia.pageobjects.ArticlePage
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
    private val articlePage = ArticlePage()
    private val searchSupport = SearchSupport()

    private val searchPhrase = "Dog"

    @Before
    fun skipOnboarding() {
        onboardingPage.clickSkipButton()
    }

    @Test
    fun shouldBePossibleToSearchFromExploreScreen() {
        searchSupport.searchFromExploreScreen(searchPhrase)
        assertTrue("Article header does not contain '$searchPhrase'",
            articlePage.doesArticleHeaderContain(searchPhrase))
    }

    @Test
    fun shouldBePossibleToSearchFromSearchScreen() {
        tabBar.clickSearchButton()
        searchPage.clickSearchBar()
        activeSearchPage.searchAndSelectItem(searchPhrase)
        assertTrue("Article header does not contain '$searchPhrase'",
            articlePage.doesArticleHeaderContain(searchPhrase))
    }

    @Test
    fun shouldBePossibleToSelectItemFromSearchHistory() {
        searchSupport.searchFromExploreScreen(searchPhrase)
        pressBack()
        activeSearchPage.clearSearchTextField()
        activeSearchPage.selectItemFromSearchHistory(searchPhrase)
        assertTrue("Search text field does not contain '$searchPhrase'",
            activeSearchPage.doesSearchTextFieldContain(searchPhrase))
    }

    @Test
    fun shouldBePossibleToDeleteSearchHistory() {
        searchSupport.searchFromExploreScreen(searchPhrase)
        pressBack()
        activeSearchPage.clearSearchTextField()
        activeSearchPage.deleteSearchHistory()
        assertTrue("Delete history button is displayed",
            activeSearchPage.isDeleteHistoryButtonNotDisplayed())
    }

    @Test
    fun shouldBePossibleToSelectItemFromBrowsingHistory() {
        searchSupport.searchFromExploreScreen(searchPhrase)
        repeat(3) { pressBack() }
        tabBar.clickSearchButton()
        searchPage.selectItemFromBrowsingHistory(searchPhrase)
        assertTrue("Article header does not contain '$searchPhrase'",
            articlePage.doesArticleHeaderContain(searchPhrase))
    }

    @Test
    fun shouldBePossibleToDeleteBrowsingHistory() {
        searchSupport.searchFromExploreScreen(searchPhrase)
        repeat(3) { pressBack() }
        tabBar.clickSearchButton()
        searchPage.deleteBrowsingHistory()
        assertTrue("Delete history button is displayed",
            searchPage.isDeleteHistoryButtonNotDisplayed())
    }
}