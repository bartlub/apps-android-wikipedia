package org.wikipedia.pageobjects

import androidx.test.espresso.matcher.ViewMatchers.withId
import org.wikipedia.R
import org.wikipedia.base.BasePage

class ExplorePage: BasePage() {

    private val searchBar = withId(R.id.search_container)

    fun clickSearchBar() {
        clickElement(searchBar)
    }
}