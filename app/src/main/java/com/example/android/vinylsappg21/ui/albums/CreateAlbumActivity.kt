package com.example.android.vinylsappg21.ui.albums

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.vinylsappg21.R

class CreateAlbumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_album)
        // showing the back button in action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}