package org.wikipedia.pageobjects

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.wikipedia.R
import org.wikipedia.base.BasePage

class SearchPage: BasePage() {

    private val searchBar = withId(R.id.search_card)
    private val browsingHistoryList = withId(R.id.history_list)
    private val deleteHistoryButton = withId(R.id.history_delete)
    private val yesButton = withText("YES")
    private fun browsingHistoryItemTitle(itemName: String): Matcher<View> {
        return allOf(withId(R.id.page_list_item_title), withText(itemName))
    }

    fun clickSearchBar() {
        clickElement(searchBar)
    }

    fun selectItemFromBrowsingHistory(itemName: String) {
        clickDescendantElementInRecyclerView(browsingHistoryList, browsingHistoryItemTitle(itemName))
    }

    fun deleteBrowsingHistory() {
        clickElement(deleteHistoryButton)
        clickElement(yesButton)
    }

    fun isDeleteHistoryButtonNotDisplayed(): Boolean {
        return isNotDisplayed(deleteHistoryButton)
    }
}