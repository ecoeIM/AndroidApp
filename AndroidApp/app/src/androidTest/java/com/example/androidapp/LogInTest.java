package com.example.androidapp;

//-----------------------------------
//This can be only tested when the user is logged in
//-----------------------------------


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasFocus;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class LogInTest {
    @Rule
    public ActivityTestRule<MainActivity> mListActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void LogInUi() throws Exception {
        onView(withId(R.id.edit_text_email_sign_in))
                .perform(typeText("espressotest@mail.com"));
        onView(withId(R.id.edit_text_password_sign_in))
                .perform(typeText("espressotest"));
        onView(withId(R.id.login))
                .perform(click());
        onView(withId(R.id.imageButtonSettings))
                .perform(click());
        //check if user is logged in
        onView(withText("espressotest@mail.com"))
                .check(matches(isDisplayed()));
    }
}
