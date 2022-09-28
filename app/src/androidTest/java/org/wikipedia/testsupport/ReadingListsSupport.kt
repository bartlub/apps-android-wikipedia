package org.wikipedia.testsupport

import org.wikipedia.pageobjects.SavedPage
import org.wikipedia.pageobjects.dialogs.ReadingListDialog

class ReadingListsSupport {

    private val savedPage = SavedPage()
    private val readingListDialog = ReadingListDialog()

    fun createNewReadingList(listName: String, description: String) {
        savedPage.clickMoreMenuButton()
        savedPage.clickCreateNewListButton()
        readingListDialog.fillInFieldsAndConfirm(listName, description)
    }
}