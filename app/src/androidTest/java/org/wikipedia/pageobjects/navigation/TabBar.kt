package org.wikipedia.pageobjects.navigation

import androidx.test.espresso.matcher.ViewMatchers.withId
import org.wikipedia.R
import org.wikipedia.base.BasePage

class TabBar: BasePage() {

    private val savedButton = withId(R.id.nav_tab_reading_lists)
    private val searchButton = withId(R.id.nav_tab_search)
    private val moreButton = withId(R.id.nav_more_container)

    fun clickSavedButton() {
        clickElement(savedButton)
    }

    fun clickSearchButton() {
        clickElement(searchButton)
    }

    fun clickMoreButton() {
        clickElement(moreButton)
    }
}