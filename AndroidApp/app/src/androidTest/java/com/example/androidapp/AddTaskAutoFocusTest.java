package com.example.androidapp;


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

//-----------------------------------
//This can be only tested when the user is logged out
//-----------------------------------


@RunWith(AndroidJUnit4.class)
public class AddTaskAutoFocusTest {
    @Rule
    public ActivityTestRule<MainActivity> mListActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void AddTaskUi() throws Exception {
        onView(withId(R.id.navigation_tasks))
                .perform(click());

        onView(withId(R.id.fab_create_task))
                .perform(click());

        //check if element is focused after being opened
        onView(withId(R.id.edit_text_task_name))
                .check(matches(hasFocus()));
    }
}
