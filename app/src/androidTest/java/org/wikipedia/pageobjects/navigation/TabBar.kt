package org.wikipedia.pageobjects.navigation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.wikipedia.R

class TabBar {

    private val searchButton = R.id.nav_tab_search
    private val moreButton = R.id.nav_more_container

    fun clickSearchButton() {
        onView(withId(searchButton)).perform(click())
    }

    fun clickMoreButton() {
        onView(withId(moreButton)).perform(click())
    }
}