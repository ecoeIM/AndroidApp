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

@RunWith(AndroidJUnit4.class)
public class AddProfileTest {
    @Rule
    public ActivityTestRule<MainActivity> mListActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void AddProfileUi() throws Exception {
        onView(withId(R.id.imageButtonSettings))
                .perform(click());

        onView(withId(R.id.image_button_add_profile))
                .perform(click());

        onView(withId(R.id.edit_text_profile_name))
                .perform(typeText("espressotest"));

        onView(withId(R.id.edit_text_temp_min))
                .perform(typeText("20"));
        onView(withId(R.id.edit_text_temp_max))
                .perform(typeText("40"));
        onView(withId(R.id.edit_text_hum_min))
                .perform(typeText("20"));
        onView(withId(R.id.edit_text_hum_max))
                .perform(typeText("40"));
        onView(withId(R.id.edit_text_co2_min))
                .perform(typeText("20"));
        onView(withId(R.id.edit_text_co2_max))
                .perform(typeText("40"));
        onView(withId(R.id.edit_text_light_min))
                .perform(typeText("20"));
        onView(withId(R.id.edit_text_light_max))
                .perform(typeText("40"));

        onView(withText("Add")).perform(click());

        //check if element is focused after being opened
        onView(withText("espressotest"))
                .check(matches(isDisplayed()));
    }
}
