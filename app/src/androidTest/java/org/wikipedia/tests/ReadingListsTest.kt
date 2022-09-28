package org.wikipedia.tests

import androidx.test.espresso.Espresso.pressBack
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.wikipedia.base.BaseTest
import org.wikipedia.pageobjects.ArticlePage
import org.wikipedia.pageobjects.OnboardingPage
import org.wikipedia.pageobjects.ReadingListPage
import org.wikipedia.pageobjects.SavedPage
import org.wikipedia.pageobjects.dialogs.ReadingListDialog
import org.wikipedia.pageobjects.navigation.TabBar
import org.wikipedia.testsupport.ReadingListsSupport
import org.wikipedia.testsupport.SearchSupport

@RunWith(AndroidJUnit4::class)
class ReadingListsTest: BaseTest() {

    private val onboardingPage = OnboardingPage()
    private val tabBar = TabBar()
    private val articlePage = ArticlePage()
    private val searchSupport = SearchSupport()
    private val savedPage = SavedPage()
    private val readingListPage = ReadingListPage()
    private val readingListDialog = ReadingListDialog()
    private val readingListsSupport = ReadingListsSupport()

    private val searchPhrase = "Cat"
    private val defaultReadingListName = "Saved"
    private val readingListName = "Animals"
    private val readingListDescription = "My favourite animals"
    private val newReadingListName = "Tetrapods"
    private val newReadingListDescription = "My favourite tetrapods"

    @Before
    fun skipOnboarding() {
        onboardingPage.clickSkipButton()
    }

    @Test
    fun shouldBePossibleToCreateReadingList() {
        tabBar.clickSavedButton()
        readingListsSupport.createNewReadingList(readingListName, readingListDescription)
        assertTrue("Reading list title and/or description are not displayed",
            savedPage.isReadingListTitleDisplayed(readingListName)
                    && savedPage.isReadingListDescriptionDisplayed(readingListDescription)
        )
    }

    @Test
    fun shouldBePossibleToRenameReadingList() {
        tabBar.clickSavedButton()
        readingListsSupport.createNewReadingList(readingListName, readingListDescription)
        savedPage.clickReadingList(readingListName)
        readingListPage.clickMoreMenuButton()
        readingListPage.clickEditNameButton()
        readingListDialog.fillInFieldsAndConfirm(newReadingListName, newReadingListDescription)
        assertTrue("Reading list title and/or description are not correct",
            readingListPage.doesListTitleContain(newReadingListName)
                    && readingListPage.doesListDescriptionContain(newReadingListDescription)
        )
    }

    @Test
    fun shouldBePossibleToRemoveReadingList() {
        tabBar.clickSavedButton()
        readingListsSupport.createNewReadingList(readingListName, readingListDescription)
        savedPage.clickReadingList(readingListName)
        readingListPage.deleteList()
        assertFalse("Reading list is still displayed",
            savedPage.isReadingListTitleDisplayed(readingListName)
        )
    }

    @Test
    fun shouldBePossibleToSaveArticleToNewReadingList() {
        searchSupport.searchFromExploreScreen(searchPhrase)
        articlePage.clickSaveButton()
        articlePage.clickAddToListButton()
        readingListDialog.fillInFieldsAndConfirm(readingListName, readingListDescription)
        repeat(3) { pressBack() }
        tabBar.clickSavedButton()
        savedPage.clickReadingList(readingListName)
        assertTrue("Saved article is not displayed",
            readingListPage.isSavedArticleDisplayed(searchPhrase)
        )
    }

    @Test
    fun shouldBePossibleToRemoveSavedArticle() {
        searchSupport.searchFromExploreScreen(searchPhrase)
        articlePage.clickSaveButton()
        repeat(3) { pressBack() }
        tabBar.clickSavedButton()
        savedPage.clickReadingList(defaultReadingListName)
        readingListPage.clickSavedArticle(searchPhrase)
        articlePage.removeArticleFromReadingList(defaultReadingListName)
        pressBack()
        assertTrue("Reading list is not empty", readingListPage.isReadingListEmpty())
    }
}