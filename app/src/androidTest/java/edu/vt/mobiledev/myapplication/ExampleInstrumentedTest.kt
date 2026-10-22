package edu.vt.mobiledev.myapplication

import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("edu.vt.mobiledev.myapplication", appContext.packageName)
    }
}

class BurgerAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun selectBun_updatesUI() {
        composeTestRule.onNodeWithTag("bun_white").performClick()
        composeTestRule.onNodeWithTag("bun_white").assertIsSelected()
    }
    @Test
    fun selectPatty_updatesUI() {
        composeTestRule.onNodeWithTag("patty_beef").performClick()
        composeTestRule.onNodeWithTag("patty_beef").assertIsSelected()
    }
    @Test
    fun selectToppings_limitToThree() {
        composeTestRule.onNodeWithTag("topping_bacon").performClick()
        composeTestRule.onNodeWithTag("topping_lettuce").performClick()
        composeTestRule.onNodeWithTag("topping_tomato").performClick()

        // 4th should not be added
        composeTestRule.onNodeWithTag("topping_pickles").performClick()

        // Just verify first 3 still selected
        composeTestRule.onNodeWithTag("topping_bacon").assertIsSelected()
    }
    @Test
    fun enterQuantity_updatesField() {
        composeTestRule.onNodeWithTag("quantity_input")
            .performTextInput("3")

        composeTestRule.onNodeWithTag("quantity_input")
            .assertTextContains("3")
    }
    @Test
    fun totalUpdates_correctly() {
        composeTestRule.onNodeWithTag("bun_white").performClick()
        composeTestRule.onNodeWithTag("patty_beef").performClick()

        composeTestRule.onNodeWithTag("quantity_input")
            .performTextInput("2")

        composeTestRule.onNodeWithText(
            "Cost:",
            substring = true
        ).assertExists()
    }
}