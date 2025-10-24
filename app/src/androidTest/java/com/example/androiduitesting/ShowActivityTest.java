package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * UI tests for the ShowActivity flow.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowActivityTest {

    // Rule to launch MainActivity before each test
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Tests if clicking a city in the list switches to ShowActivity.
     */
    @Test
    public void testActivitySwitch() {
        // Add a new city to test with
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Calgary"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city in the ListView
        onData(is(instanceOf(String.class))).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        // Check if ShowActivity is displayed by verifying its unique view is present
        onView(withId(R.id.textView_cityName)).check(matches(isDisplayed()));
    }

    /**
     * Tests if the city name displayed in ShowActivity is correct.
     */
    @Test
    public void testCityNameConsistency() {
        // Add a new city to test with
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Calgary"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city in the ListView
        onData(is(instanceOf(String.class))).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        // Check if the TextView in ShowActivity shows the correct city name
        onView(withId(R.id.textView_cityName)).check(matches(withText("Calgary")));
    }

    /**
     * Tests if the back button in ShowActivity returns to MainActivity.
     */
    @Test
    public void testBackButton() {
        // Add a city and navigate to ShowActivity
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Calgary"));
        onView(withId(R.id.button_confirm)).perform(click());
        onData(is(instanceOf(String.class))).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        // Click the back button in ShowActivity
        onView(withId(R.id.button_back)).perform(click());

        // Check if we are back in MainActivity by verifying one of its views is displayed
        onView(withId(R.id.button_add)).check(matches(isDisplayed()));
    }
}
