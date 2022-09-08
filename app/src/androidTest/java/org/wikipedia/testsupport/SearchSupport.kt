package org.wikipedia.testsupport

import org.wikipedia.pageobjects.ActiveSearchPage
import org.wikipedia.pageobjects.ExplorePage

class SearchSupport {

    private val explorePage = ExplorePage()
    private val activeSearchPage = ActiveSearchPage()

    fun searchFromExploreScreen(text: String) {
        explorePage.clickSearchBar()
        activeSearchPage.searchAndSelectItem(text)
    }
}