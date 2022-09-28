package org.wikipedia.pageobjects

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.wikipedia.R
import org.wikipedia.base.BasePage

class SavedPage: BasePage() {

    private fun readingListTitle(readingListName: String): Matcher<View> =
        allOf(withId(R.id.item_title), withText(readingListName))
    private fun readingListDescription(readingListDescription: String): Matcher<View> =
        allOf(withId(R.id.item_description), withText(readingListDescription))
    private val moreMenuButton = withId(R.id.menu_overflow_button)
    private val createNewListButton = withId(R.id.reading_lists_overflow_create_new_list)

    fun clickReadingList(readingListName: String) {
        clickElement(readingListTitle(readingListName))
    }

    fun clickMoreMenuButton() {
        clickElement(moreMenuButton)
    }

    fun clickCreateNewListButton() {
        clickElement(createNewListButton)
    }

    fun isReadingListTitleDisplayed(readingListName: String) =
        isDisplayed(readingListTitle(readingListName))

    fun isReadingListDescriptionDisplayed(readingListDescription: String) =
        isDisplayed(readingListDescription(readingListDescription))
}