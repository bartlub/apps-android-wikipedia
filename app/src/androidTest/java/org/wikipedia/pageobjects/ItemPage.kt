package org.wikipedia.pageobjects

import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.webdriver.DriverAtoms.findElement
import androidx.test.espresso.web.webdriver.Locator.CLASS_NAME

class ItemPage {

    private val itemHeader = "mw-page-title-main"

    fun getItemHeader() = onWebView().withElement(findElement(CLASS_NAME, itemHeader))
}