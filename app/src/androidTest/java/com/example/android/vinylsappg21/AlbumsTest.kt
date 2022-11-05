package com.example.android.vinylsappg21


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.adevinta.android.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition
import com.example.android.vinylsappg21.ui.adapters.AlbumsAdapter
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AlbumsTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    private val titleAlbum = "A Day at the Races"
    private val titleAlbum2 = "A Night at the Opera"
    private val titleAlbum3 = "Buscando América"

    //@Test
    fun accessAlbumFromMenuTest(){
        onView(withId(R.id.visitor1)).perform(click())
        onView(withContentDescription(R.string.navigation_drawer_open)).perform(click())
        onView(withId(R.id.nav_albums_menu)).perform(click())
        onView(withId(R.id.albums_fragment_title)).check(matches(withText("Álbums")))
    }

    //@Test
    fun newAlbumButtonNotDisplayedTest(){
        onView(withId(R.id.visitor1)).perform(click())
        onView(withId(R.id.fetch_button)).check(matches(Matchers.not(isDisplayed())))
    }

    //@Test
    fun albumsListIsDisplayedTest(){
        onView(withId(R.id.visitor1)).perform(click())
        onView(withId(R.id.albumsRv)).check(matches(isDisplayed()))
    }

    //@Test
    fun checkAlbumNameDisplayedTest(){
        onView(withId(R.id.visitor1)).perform(click())
        onView(withId(R.id.albumsRv))
            .perform(actionOnItemAtPosition<AlbumsAdapter.AlbumViewHolder>(1, click()))
        onView(withText(titleAlbum)).check(matches(isDisplayed()))
    }

    @Test
    fun checkOrderedListAlbumTest(){
        onView(withId(R.id.visitor1)).perform(click())
        assertDisplayedAtPosition(R.id.albumsRv, 0, R.id.textView6, titleAlbum)
        assertDisplayedAtPosition(R.id.albumsRv, 1, R.id.textView6, titleAlbum2)
        assertDisplayedAtPosition(R.id.albumsRv, 2, R.id.textView6, titleAlbum3)
    }

    //@Test
    fun scrollToAlbumListTest(){
        onView(withId(R.id.visitor1)).perform(click())
        onView(withId(R.id.albumsRv))
            .perform(scrollToPosition<AlbumsAdapter.AlbumViewHolder>(-1))
        onView(withId(R.id.albumsRv))
            .perform(scrollToPosition<AlbumsAdapter.AlbumViewHolder>(1))
    }



}
