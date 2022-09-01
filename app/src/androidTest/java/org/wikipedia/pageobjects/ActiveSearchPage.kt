package org.wikipedia.pageobjects

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.allOf
import org.wikipedia.R
import org.wikipedia.TestUtil

class ActiveSearchPage {

    private val searchTextField = R.id.search_src_text
    private val recentSearchesList = R.id.recent_searches_recycler
    private val itemTitle = R.id.page_list_item_title
    private val deleteHistoryButton = R.id.recent_searches_delete_button
    private val yesButton = "YES"

    fun getSearchTextField() = onView(withId(searchTextField))

    fun getDeleteHistoryButton() = onView(withId(deleteHistoryButton))

    fun searchAndSelectItem(text: String) {
        getSearchTextField().perform(typeText(text))
        TestUtil.waitForView(itemTitle, 3)
        onView(allOf(withId(itemTitle), withText(text))).perform(click())
    }

    fun clearSearchTextField() {
        getSearchTextField().perform(clearText())
    }

    fun selectItemFromSearchHistory(itemNumber: Int) {
        onView(withId(recentSearchesList))
            .perform(actionOnItemAtPosition<ViewHolder>(itemNumber - 1, click()))
    }

    fun deleteSearchHistory() {
        getDeleteHistoryButton().perform(click())
        onView(withText(yesButton)).perform(click())
    }
}