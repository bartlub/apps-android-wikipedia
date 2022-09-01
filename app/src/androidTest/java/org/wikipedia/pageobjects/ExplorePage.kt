package org.wikipedia.pageobjects

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.wikipedia.R

class ExplorePage {

    private val searchBar = R.id.search_container

    fun clickSearchBar() {
        onView(withId(searchBar)).perform(click())
    }
}