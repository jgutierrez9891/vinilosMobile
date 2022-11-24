package com.example.android.vinylsappg21.ui.albums

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.vinylsappg21.R

class NewTrackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_track)
        // showing the back button in action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}