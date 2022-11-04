package com.example.android.vinylsappg21

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.android.vinylsappg21.ui.albums.AlbumsFragment
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AlbumsTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(AlbumsActivity::class.java)

    @Test
    fun checkTitleTest(){
        launchFragmentInContainer<AlbumsFragment>()
        onView(withId(R.id.albums_fragment_title)).check(matches(withText("Álbums")))
    }

    @Test
    fun checkNewAlbumButtonTest(){
        launchFragmentInContainer<AlbumsFragment>()
        onView(withId(R.id.fetch_button)).check(matches(withText("NUEVO ÁLBUM")))
    }





}