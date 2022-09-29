package org.wikipedia.pageobjects.navigation

import androidx.test.espresso.matcher.ViewMatchers.withId
import org.wikipedia.R
import org.wikipedia.base.BasePage

class MoreMenu: BasePage() {

    private val settingsButton = withId(R.id.main_drawer_settings_container)
    private val loginButton = withId(R.id.main_drawer_login_button)

    fun clickSettingsButton() {
        clickElement(settingsButton)
    }

    fun clickLoginButton() {
        clickElement(loginButton)
    }
}