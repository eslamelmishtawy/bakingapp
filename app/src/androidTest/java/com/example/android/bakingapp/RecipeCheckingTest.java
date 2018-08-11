package com.example.android.bakingapp;


import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.rule.ActivityTestRule;
import static android.support.test.espresso.Espresso.onData;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.bakingapp.UI.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class RecipeCheckingTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule(MainActivity.class);

    @Test
    public void contentTest() {

        onView(withId(R.id.test)).check(matches(isDisplayed()));

    }

}
