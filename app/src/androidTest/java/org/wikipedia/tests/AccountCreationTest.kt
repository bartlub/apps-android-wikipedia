package org.wikipedia.tests

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.wikipedia.TestUtil
import org.wikipedia.base.BaseTest
import org.wikipedia.pageobjects.AccountCreationPage
import org.wikipedia.pageobjects.OnboardingPage
import org.wikipedia.pageobjects.navigation.MoreMenu
import org.wikipedia.pageobjects.navigation.TabBar

class AccountCreationTest: BaseTest() {

    private val onboardingPage = OnboardingPage()
    private val tabBar = TabBar()
    private val moreMenu = MoreMenu()
    private val accountCreationPage = AccountCreationPage()

    private val existingUsername = "user"
    private val newUsername = "new_user"
    private val validPassword = "password"
    private val invalidPassword = "pass"

    @Before
    fun goToAccountCreationPage() {
        onboardingPage.clickSkipButton()
        tabBar.clickMoreButton()
        moreMenu.clickLoginButton()
    }

    @Test
    fun shouldNotBePossibleToCreateAccountWhenPasswordIsNotProvided() {
        accountCreationPage.enterUsername(newUsername)
        Assert.assertFalse("Next button is enabled",
            accountCreationPage.isNextButtonEnabled())
    }

    @Test
    fun shouldNotBePossibleToCreateAccountWhenUsernameIsNotProvided() {
        accountCreationPage.enterAndRepeatPassword(validPassword)
        Assert.assertFalse("Next button is enabled",
            accountCreationPage.isNextButtonEnabled())
    }

    @Test
    fun shouldDisplayErrorWhenUsernameIsTaken() {
        accountCreationPage.enterUsername(existingUsername)
        TestUtil.waitForTextFieldError(3)
        Assert.assertTrue("Username taken error is not displayed",
            accountCreationPage.isUsernameTakenErrorDisplayed(existingUsername)
        )
    }

    @Test
    fun shouldDisplayErrorWhenPasswordIsTooShort() {
        accountCreationPage.createAccount(newUsername, invalidPassword)
        Assert.assertTrue("Too short password error is not displayed",
            accountCreationPage.isTooShortPasswordErrorDisplayed()
        )
    }

    @Test
    fun shouldDisplayErrorWhenPasswordsDoNotMatch() {
        accountCreationPage.enterUsername(newUsername)
        accountCreationPage.enterPassword(validPassword)
        accountCreationPage.repeatPassword(invalidPassword)
        accountCreationPage.clickNextButton()
        Assert.assertTrue("Passwords mismatch error is not displayed",
            accountCreationPage.isPasswordsMismatchErrorDisplayed()
        )
    }
}