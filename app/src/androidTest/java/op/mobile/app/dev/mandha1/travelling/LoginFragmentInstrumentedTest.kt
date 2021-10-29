package op.mobile.app.dev.mandha1.travelling

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginFragmentInstrumentedTest {

    // lateinit allows you to initialise a variable later
    private lateinit var emailAddressToBeTyped: String
    private lateinit var passwordToBeTyped: String

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun initValidString() {
        // Initialising variables marked with the lateinit keyword
        emailAddressToBeTyped = "testemail"
        passwordToBeTyped = "testemail"
    }

    @Test
    fun login() {
        // withId(R.id.et_email_address) is a ViewMatcher
        onView(withId(R.id.et_username))
            // typeText() is a ViewAction
            .perform(typeText(emailAddressToBeTyped), closeSoftKeyboard())

        onView(withId(R.id.et_password))
            .perform(typeText(passwordToBeTyped), closeSoftKeyboard())

        // click() is a ViewAction
        onView(withId(R.id.btn_login)).perform(click())
        onView(withId(R.id.tv_status)).check(matches(isDisplayed()))
    }
}