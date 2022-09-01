package org.wikipedia.pageobjects

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.allOf
import org.wikipedia.R

class SearchPage {

    private val searchBar = R.id.search_card
    private val itemTitle = R.id.page_list_item_title
    private val deleteHistoryButton = R.id.history_delete
    private val yesButton = "YES"

    fun getDeleteHistoryButton() = onView(withId(deleteHistoryButton))

    fun clickSearchBar() {
        onView(withId(searchBar)).perform(click())
    }

    fun selectItemFromBrowsingHistory(text: String) {
        onView(allOf(withId(itemTitle), withText(text))).perform(click())
    }

    fun deleteBrowsingHistory() {
        getDeleteHistoryButton().perform(click())
        onView(withText(yesButton)).perform(click())
    }
}