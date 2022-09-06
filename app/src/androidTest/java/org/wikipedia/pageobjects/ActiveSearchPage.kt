package org.wikipedia.pageobjects

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.wikipedia.R
import org.wikipedia.TestUtil
import org.wikipedia.base.BasePage

class ActiveSearchPage: BasePage() {

    private val searchTextField = withId(R.id.search_src_text)
    private val searchHistoryList = withId(R.id.recent_searches_recycler)
    private val deleteHistoryButton = withId(R.id.recent_searches_delete_button)
    private val yesButton = withText("YES")
    private val itemTitleId = R.id.page_list_item_title
    private fun itemTitle(itemName: String): Matcher<View> {
        return allOf(withId(itemTitleId), withText(itemName))
    }
    private fun searchHistoryItemTitle(itemName: String): Matcher<View> {
        return withText(itemName)
    }

    fun searchAndSelectItem(itemName: String) {
        enterText(searchTextField, itemName)
        TestUtil.waitForView(itemTitleId, 3)
        clickElement(itemTitle(itemName))
    }

    fun clearSearchTextField() {
        clearText(searchTextField)
    }

    fun selectItemFromSearchHistory(itemName: String) {
        clickElementInRecyclerView(searchHistoryList, searchHistoryItemTitle(itemName))
    }

    fun deleteSearchHistory() {
        clickElement(deleteHistoryButton)
        clickElement(yesButton)
    }

    fun doesSearchTextFieldContain(text: String): Boolean {
        return doesElementContainText(searchTextField, text)
    }

    fun isDeleteHistoryButtonNotDisplayed(): Boolean {
        return isNotDisplayed(deleteHistoryButton)
    }
}