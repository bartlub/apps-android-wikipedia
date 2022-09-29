package org.wikipedia.tests

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.wikipedia.TestUtil
import org.wikipedia.base.BaseTest
import org.wikipedia.pageobjects.AccountCreationPage
import org.wikipedia.pageobjects.LoginPage
import org.wikipedia.pageobjects.OnboardingPage
import org.wikipedia.pageobjects.navigation.MoreMenu
import org.wikipedia.pageobjects.navigation.TabBar

class LoginTest: BaseTest() {

    private val onboardingPage = OnboardingPage()
    private val tabBar = TabBar()
    private val moreMenu = MoreMenu()
    private val accountCreationPage = AccountCreationPage()
    private val loginPage = LoginPage()

    private val username = "username"
    private val password = "password"

    @Before
    fun goToLoginPage() {
        onboardingPage.clickSkipButton()
        tabBar.clickMoreButton()
        moreMenu.clickLoginButton()
        accountCreationPage.clickLoginButton()
    }

    @Test
    fun shouldNotBePossibleToLogInWhenPasswordIsNotProvided() {
        loginPage.enterUsername(username)
        assertFalse("Login button is enabled", loginPage.isLoginButtonEnabled())
    }

    @Test
    fun shouldNotBePossibleToLogInWhenUsernameIsNotProvided() {
        loginPage.enterPassword(password)
        assertFalse("Login button is enabled", loginPage.isLoginButtonEnabled())
    }

    @Test
    fun shouldDisplayErrorWhenCredentialsAreInvalid() {
        loginPage.logIn(username, password)
        TestUtil.waitForSnackbar(3)
        assertTrue("Incorrect credentials error is not displayed",
            loginPage.isIncorrectCredentialsErrorDisplayed())
    }
}