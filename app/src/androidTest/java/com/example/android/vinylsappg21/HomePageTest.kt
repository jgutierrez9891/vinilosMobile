package com.example.android.vinylsappg21

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class HomePageTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loginAsVisitorTest(){
        onView(withId(R.id.visitor1)).perform(click())
        onView(withId(R.id.albums_fragment_title)).check(matches(withText("Álbums")))
    }

    @Test
    fun loginAsCollectorTest(){
        onView(withId(R.id.collector1)).perform(click())
        onView(withId(R.id.albums_fragment_title)).check(matches(withText("Álbums")))
        onView(withId(R.id.fetch_button)).check(matches(isDisplayed()))
    }

    @Test
    fun checkStartMessageTest(){
        onView(withId(R.id.text_body)).check(matches(withText("Ingresar Como")))
    }

    @Test
    fun checkNameAppTest(){
        onView(withId(R.id.text_title)).check((matches(withText("Vinilos App"))))
    }

    @Test
    fun accessArtistFromMenuTest(){
        onView(withId(R.id.visitor1)).perform(click())
        onView(withContentDescription(R.string.navigation_drawer_open)).perform(click())
        onView(withId(R.id.nav_artists_menu)).perform(click())
        onView(withId(R.id.text_gallery)).check(matches(withText("This is artists Fragment")))
    }

}