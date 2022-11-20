package com.example.android.vinylsappg21

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.adevinta.android.barista.assertion.BaristaListAssertions
import com.example.android.vinylsappg21.ui.adapters.AlbumsAdapter
import com.example.android.vinylsappg21.ui.adapters.ArtistsAdapter
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test

class ColeccionistaTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    private val nameCollector1 = "Jaime Monsalve"
    private val nameCollector2 = "Manolo Bellon"

    @Test
    fun accessColectorFromMenuTest(){
        onView(withId(R.id.visitor1)).perform(click())
        onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open))
            .perform(click())
        onView(withId(R.id.nav_collectors_menu)).perform(click())
        onView(withId(R.id.colectors_fragment_title)).check(matches(withText("Coleccionistas")))
    }

    @Test
    fun colectorListIsDisplayedTest(){
        onView(withId(R.id.visitor1)).perform(click())
        onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open)).perform(click())
        onView(withId(R.id.nav_collectors_menu)).perform(click())
        onView(withId(R.id.collectorsRv)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkOrderedListColectorTest(){
        onView(withId(R.id.visitor1)).perform(click())
        onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open)).perform(click())
        onView(withId(R.id.nav_collectors_menu)).perform(click())
        BaristaListAssertions.assertDisplayedAtPosition(R.id.collectorsRv, 0, R.id.textView6, nameCollector1)
        BaristaListAssertions.assertDisplayedAtPosition(R.id.collectorsRv, 1, R.id.textView6, nameCollector2)
    }

    @Test
    fun scrollToColectorListTest(){
        onView(withId(R.id.visitor1)).perform(click())
        onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open)).perform(click())
        onView(withId(R.id.nav_collectors_menu)).perform(click())
        onView(withId(R.id.collectorsRv))
            .perform(RecyclerViewActions.scrollToPosition<ArtistsAdapter.ArtistViewHolder>(-1))
        onView(withId(R.id.collectorsRv))
            .perform(RecyclerViewActions.scrollToPosition<ArtistsAdapter.ArtistViewHolder>(1))
    }

    @Test
    fun checkTextSectionColectorTest(){
        onView(withId(R.id.visitor1)).perform(click())
        onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open)).perform(click())
        onView(withId(R.id.nav_collectors_menu)).perform(click())
        onView(withId(R.id.collectors_fragment_desc)).check(matches(withText("Encuentra coleccionistas")))
    }

}