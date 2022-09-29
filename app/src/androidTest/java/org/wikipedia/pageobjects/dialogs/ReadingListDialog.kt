package org.wikipedia.pageobjects.dialogs

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.wikipedia.R
import org.wikipedia.base.BasePage

class ReadingListDialog: BasePage() {

    private val listNameTextField = withId(R.id.text_input)
    private val descriptionTextField = withId(R.id.secondary_text_input)
    private val okButton = withText("OK")

    fun fillInFieldsAndConfirm(listName: String, description: String) {
        clearText(listNameTextField)
        enterText(listNameTextField, listName)
        clearText(descriptionTextField)
        enterText(descriptionTextField, description)
        clickElement(okButton)
    }
}