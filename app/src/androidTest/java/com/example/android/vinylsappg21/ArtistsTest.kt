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

class ArtistsTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    private val nameArtist = "Francisca Viveros Barradas"
    private val nameArtist2 = "Joan Manuel Serrat Teresa"
    private val nameArtist3 = "Paul David Hewson"

    @Test
    fun accessArtistFromMenuTest(){
        onView(withId(R.id.visitor1)).perform(click())
        onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open))
            .perform(click())
        onView(withId(R.id.nav_artists_menu)).perform(click())
        onView(withId(R.id.artists_fragment_title)).check(matches(withText("Artistas")))
    }

    @Test
    fun artistsListIsDisplayedTest(){
        onView(withId(R.id.visitor1)).perform(click())
        onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open))
            .perform(click())
        onView(withId(R.id.nav_artists_menu)).perform(click())
        onView(withId(R.id.artistsRv)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkArtistNameDisplayedTest(){
        onView(withId(R.id.visitor1)).perform(click())
        onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open)).perform(click())
        onView(withId(R.id.nav_artists_menu)).perform(click())
        onView(withId(R.id.artistsRv))
            .perform(actionOnItemAtPosition<ArtistsAdapter.ArtistViewHolder>(1, click()))
        onView(withText(nameArtist2)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkOrderedListAlbumTest(){
        onView(withId(R.id.visitor1)).perform(click())
        onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open)).perform(click())
        onView(withId(R.id.nav_artists_menu)).perform(click())
        BaristaListAssertions.assertDisplayedAtPosition(R.id.artistsRv, 0, R.id.textView6, nameArtist)
        BaristaListAssertions.assertDisplayedAtPosition(R.id.artistsRv, 1, R.id.textView6, nameArtist2)
        BaristaListAssertions.assertDisplayedAtPosition(R.id.artistsRv, 2, R.id.textView6, nameArtist3)
    }

    @Test
    fun scrollToArtistListTest(){
        onView(withId(R.id.visitor1)).perform(click())
        onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open)).perform(click())
        onView(withId(R.id.nav_artists_menu)).perform(click())
        onView(withId(R.id.artistsRv))
            .perform(RecyclerViewActions.scrollToPosition<ArtistsAdapter.ArtistViewHolder>(-1))
        onView(withId(R.id.artistsRv))
            .perform(RecyclerViewActions.scrollToPosition<ArtistsAdapter.ArtistViewHolder>(1))
    }

    @Test
    fun checkTextSectionArtistTest(){
        onView(withId(R.id.visitor1)).perform(click())
        onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open)).perform(click())
        onView(withId(R.id.nav_artists_menu)).perform(click())
        onView(withId(R.id.artists_fragment_desc)).check(matches(withText("Encuentra tus artistas preferidos")))
    }

}