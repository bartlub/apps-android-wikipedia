package org.wikipedia.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.web.assertion.WebViewAssertions.webMatches
import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.model.ElementReference
import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.sugar.Web.WebInteraction
import androidx.test.espresso.web.webdriver.DriverAtoms.getText
import junit.framework.AssertionFailedError
import org.hamcrest.Matcher
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.not

abstract class BasePage {

    fun getElement(element: Matcher<View>): ViewInteraction {
        return onView(element)
    }

    fun getWebElement(webElement: Atom<ElementReference>): WebInteraction<Void> {
        return onWebView().withElement(webElement)
    }

    fun clickElement(element: Matcher<View>) {
        onView(element).perform(click())
    }

    fun clickElementInScrollView(element: Matcher<View>) {
        onView(element).perform(scrollTo(), click())
    }

    fun clickElementInRecyclerView(recyclerView: Matcher<View>, element: Matcher<View>) {
        onView(recyclerView).perform(actionOnItem<ViewHolder>(element, click()))
    }

    fun clickDescendantElementInRecyclerView(recyclerView: Matcher<View>, element: Matcher<View>) {
        onView(recyclerView).perform(actionOnItem<ViewHolder>(hasDescendant(element), click()))
    }

    fun enterText(element: Matcher<View>, text: String) {
        onView(element).perform(typeText(text))
    }

    fun clearText(element: Matcher<View>) {
        onView(element).perform(clearText())
    }

    fun doesElementContainText(element: Matcher<View>, text: String): Boolean {
        return try {
            getElement(element).check(matches(withText(text)))
            true
        } catch (e: AssertionFailedError) {
            false
        }
    }

    fun doesWebElementContainText(webElement: Atom<ElementReference>, text: String): Boolean {
        return try {
            getWebElement(webElement).check(webMatches(getText(), containsString(text)))
            true
        } catch (e: AssertionFailedError) {
            false
        }
    }

    fun isDisplayed(element: Matcher<View>): Boolean {
        return try {
            getElement(element).check(matches(isDisplayed()))
            true
        } catch (e: NoMatchingViewException) {
            false
        }
    }

    fun isNotDisplayed(element: Matcher<View>): Boolean {
        return try {
            getElement(element).check(matches(not(isDisplayed())))
            true
        } catch (e: AssertionFailedError) {
            false
        }
    }

    fun doesNotExist(element: Matcher<View>): Boolean {
        return try {
            getElement(element).check(doesNotExist())
            true
        } catch (e: AssertionFailedError) {
            false
        }
    }

    fun isIntentTriggered(action: String): Boolean {
        return try {
            intended(hasAction(action))
            true
        } catch (e: AssertionFailedError) {
            false
        }
    }
}