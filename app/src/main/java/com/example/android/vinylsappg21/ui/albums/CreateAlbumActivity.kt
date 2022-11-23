package com.example.android.vinylsappg21.ui.albums

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.android.vinylsappg21.R

class CreateAlbumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_album)
        // showing the back button in action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.drawable.side_nav_bar, null))

        val genres = resources.getStringArray(R.array.Genres)
        val genresSpinner = findViewById<Spinner>(R.id.spinnerGenres)
        if(genresSpinner != null) {
            val genresAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item, genres
            )
            genresSpinner.adapter = genresAdapter
        }

        val recordLabels = resources.getStringArray(R.array.RecordLabels)
        val recordLabelsSpinner = findViewById<Spinner>(R.id.spinnerRecordLabels)
        if(recordLabelsSpinner != null) {
            val recordLabelsAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item, recordLabels
            )
            recordLabelsSpinner.adapter = recordLabelsAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}