package org.wikipedia.pageobjects

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.web.webdriver.DriverAtoms.findElement
import androidx.test.espresso.web.webdriver.Locator.CLASS_NAME
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.wikipedia.R
import org.wikipedia.base.BasePage

class ArticlePage: BasePage() {

    private val articleHeader = findElement(CLASS_NAME, "mw-page-title-main")
    private val saveButton = withId(R.id.page_save)
    private val addToListButton = allOf(withId(R.id.snackbar_action), withText("ADD TO LIST"))
    private fun removeFromReadingListButton(readingListName: String): Matcher<View> =
        withText("Remove from $readingListName")

    fun clickSaveButton() {
        clickElement(saveButton)
    }

    fun clickAddToListButton() {
        clickElement(addToListButton)
    }

    fun removeArticleFromReadingList(readingListName: String) {
        clickSaveButton()
        clickElement(removeFromReadingListButton(readingListName))
    }

    fun doesArticleHeaderContain(text: String): Boolean =
        doesWebElementContainText(articleHeader, text)
}