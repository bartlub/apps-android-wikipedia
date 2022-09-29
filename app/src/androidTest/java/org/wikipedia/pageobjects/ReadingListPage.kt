package org.wikipedia.pageobjects

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.wikipedia.R
import org.wikipedia.base.BasePage

class ReadingListPage: BasePage() {

    private val listTitle = withId(R.id.item_title)
    private val listDescription = withId(R.id.item_description)
    private fun savedArticleTitle(articleName: String): Matcher<View> =
        allOf(withId(R.id.page_list_item_title), withText(articleName))
    private val emptyListLabel = withId(R.id.reading_list_empty_text)
    private val moreMenuButton = withId(R.id.item_overflow_menu)
    private val deleteListButton = withText("Delete list")
    private val okButton = withText("OK")
    private val editNameButton = withText("Edit name/description")

    fun clickSavedArticle(articleName: String) {
        clickElement(savedArticleTitle(articleName))
    }

    fun clickMoreMenuButton() {
        clickElement(moreMenuButton)
    }

    fun clickEditNameButton() {
        clickElement(editNameButton)
    }

    fun deleteList() {
        clickMoreMenuButton()
        clickElement(deleteListButton)
        clickElement(okButton)
    }

    fun isSavedArticleDisplayed(articleName: String) = isDisplayed(savedArticleTitle(articleName))

    fun doesListTitleContain(text: String) = doesElementContainText(listTitle, text)

    fun doesListDescriptionContain(text: String) = doesElementContainText(listDescription, text)

    fun isReadingListEmpty(): Boolean = isDisplayed(emptyListLabel)
}