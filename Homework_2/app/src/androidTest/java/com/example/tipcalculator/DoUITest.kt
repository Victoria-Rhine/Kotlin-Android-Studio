package com.example.tipcalculator

import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.core.AllOf.allOf

@RunWith(AndroidJUnit4::class)
class TestCalculator {
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.tipcalculator", appContext.packageName)
    }

    @Test
    fun onLaunchCheckAmountInputIsDisplayed() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.bill_value))
            .check(matches(isDisplayed()))
    }

    @Test
    fun onLaunchCalculateButtonIsDisplayed() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withText(R.string.calculate))
            .check(matches(isDisplayed()))
    }

    @Test
    fun whenCalculateButtonIsPressedAndAmountIsEmptyAmountPerPersonIsEmpty() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.calculate))
            .perform(click())

        onView(allOf(withId(R.id.amount_per_person), withText("")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun whenCalculateButtonIsPressedAndAmountIsEmptyTotalToPayIsEmpty() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.calculate))
            .perform(click())

        onView(allOf(withId(R.id.total_to_pay), withText("")))
            .check(matches(isDisplayed()))
    }
}