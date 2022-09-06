package org.wikipedia.pageobjects.navigation

import androidx.test.espresso.matcher.ViewMatchers.withId
import org.wikipedia.R
import org.wikipedia.base.BasePage

class TabBar: BasePage() {

    private val searchButton = withId(R.id.nav_tab_search)
    private val moreButton = withId(R.id.nav_more_container)

    fun clickSearchButton() {
        clickElement(searchButton)
    }

    fun clickMoreButton() {
        clickElement(moreButton)
    }
}