package com.example.oblopgave;

import android.content.Context;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);



@Test
public void useAppContext() {

      Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.oblopgave", appContext.getPackageName());

    onView(withId(R.id.MainEmailInput)).perform(typeText("hejhej3@gmail.com"));
    onView(withId(R.id.MainEmailInput)).perform(ViewActions.closeSoftKeyboard());
    onView(withId(R.id.MainPasswordInput)).perform(typeText("Password1"));
    onView(withId(R.id.MainPasswordInput)).perform(ViewActions.closeSoftKeyboard());
    onView(withId(R.id.MainLoginButton)).perform(click());

    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    onView(withId(R.id.MainMessageView)).check(matches(withText("Login Failed")));
}


    }
