package org.wikipedia.pageobjects.navigation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.wikipedia.R

class MoreMenu {

    private val settingsButton = R.id.main_drawer_settings_container

    fun clickSettingsButton() {
        onView(withId(settingsButton)).perform(click())
    }
}