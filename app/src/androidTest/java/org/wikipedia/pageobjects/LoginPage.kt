package org.wikipedia.pageobjects

import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.wikipedia.R
import org.wikipedia.TestUtil
import org.wikipedia.base.BasePage

class LoginPage: BasePage() {

    private val usernameTextField = allOf(TestUtil.withGrandparent(withId(R.id.login_username_text)),
        withClassName(`is`("org.wikipedia.views.PlainPasteEditText")))
    private val passwordTextField = allOf(TestUtil.withGrandparent(withId(R.id.login_password_input)),
        withClassName(`is`("org.wikipedia.views.PlainPasteEditText")))
    private val loginButton = withId(R.id.login_button)
    private val incorrectCredentialsError = allOf(withId(R.id.snackbar_text),
        withText("Incorrect username or password entered.\nPlease try again."))

    fun enterUsername(username: String) {
        enterText(usernameTextField, username)
    }

    fun enterPassword(password: String) {
        enterText(passwordTextField, password)
    }

    fun logIn(username: String, password: String) {
        enterUsername(username)
        enterPassword(password)
        clickElement(loginButton)
    }

    fun isLoginButtonEnabled(): Boolean = isEnabled(loginButton)

    fun isIncorrectCredentialsErrorDisplayed(): Boolean = isDisplayed(incorrectCredentialsError)
}