package org.wikipedia.pageobjects

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.wikipedia.R
import org.wikipedia.TestUtil
import org.wikipedia.base.BasePage

class AccountCreationPage: BasePage() {

    private val usernameTextField = allOf(TestUtil.withGrandparent(withId(R.id.create_account_username)),
        withClassName(`is`("org.wikipedia.views.PlainPasteEditText")))
    private val passwordTextField = allOf(TestUtil.withGrandparent(withId(R.id.create_account_password_input)),
        withClassName(`is`("org.wikipedia.views.PlainPasteEditText")))
    private val repeatPasswordTextField = allOf(TestUtil.withGrandparent(withId(R.id.create_account_password_repeat)),
        withClassName(`is`("org.wikipedia.views.PlainPasteEditText")))
    private fun usernameTakenError(username: String): Matcher<View> = allOf(withId(R.id.textinput_error),
        withText("The user name \"$username\" is not available. Please choose a different name."))
    private val tooShortPasswordError = allOf(withId(R.id.textinput_error),
        withText("The password must be at least 8 characters"))
    private val passwordsMismatchError = allOf(withId(R.id.textinput_error),
        withText("Passwords don't match"))
    private val nextButton = withId(R.id.create_account_submit_button)
    private val loginButton = withId(R.id.create_account_login_button)

    fun enterUsername(username: String) {
        enterText(usernameTextField, username)
    }

    fun enterPassword(password: String) {
        enterText(passwordTextField, password)
    }

    fun repeatPassword(password: String) {
        enterText(repeatPasswordTextField, password)
    }

    fun enterAndRepeatPassword(password: String) {
        enterPassword(password)
        repeatPassword(password)
    }

    fun clickNextButton() {
        clickElementInScrollView(nextButton)
    }

    fun createAccount(username: String, password: String) {
        enterUsername(username)
        enterAndRepeatPassword(password)
        clickNextButton()
    }

    fun clickLoginButton() {
        clickElement(loginButton)
    }

    fun isNextButtonEnabled(): Boolean = isEnabled(nextButton)

    fun isUsernameTakenErrorDisplayed(username: String): Boolean =
        isDisplayed(usernameTakenError(username))

    fun isTooShortPasswordErrorDisplayed(): Boolean = isDisplayed(tooShortPasswordError)

    fun isPasswordsMismatchErrorDisplayed(): Boolean = isDisplayed(passwordsMismatchError)
}