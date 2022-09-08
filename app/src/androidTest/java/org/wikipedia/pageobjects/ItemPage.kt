package org.wikipedia.pageobjects

import androidx.test.espresso.web.webdriver.DriverAtoms.findElement
import androidx.test.espresso.web.webdriver.Locator.CLASS_NAME
import org.wikipedia.base.BasePage

class ItemPage: BasePage() {

    private val itemHeader = findElement(CLASS_NAME, "mw-page-title-main")

    fun doesItemHeaderContain(text: String): Boolean = doesWebElementContainText(itemHeader, text)
}