package org.wikipedia.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.wikipedia.main.MainActivity

abstract class BaseTest {

    @Rule
    @JvmField
    val mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)
}