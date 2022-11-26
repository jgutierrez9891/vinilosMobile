package com.example.android.vinylsappg21.ui.albums

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.vinylsappg21.R
import com.example.android.vinylsappg21.models.Album
import com.example.android.vinylsappg21.models.AlbumResponse
import com.example.android.vinylsappg21.viewmodels.CreateAlbumViewModel
import kotlinx.android.synthetic.main.activity_create_album.*

class CreateAlbumActivity : AppCompatActivity() {
    lateinit var viewModel: CreateAlbumViewModel
    private lateinit var recordLabelSelected: String
    private lateinit var genreSelected: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_album)

        initViewModel()

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

            genresSpinner.onItemSelectedListener = object:
                AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    genreSelected = genres[p2]
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }

        val recordLabels = resources.getStringArray(R.array.RecordLabels)
        val recordLabelsSpinner = findViewById<Spinner>(R.id.spinnerRecordLabels)
        if(recordLabelsSpinner != null) {
            val recordLabelsAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item, recordLabels
            )
            recordLabelsSpinner.adapter = recordLabelsAdapter

            recordLabelsSpinner.onItemSelectedListener = object:
                AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    recordLabelSelected = recordLabels[p2]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }

        btnNewAlbumSaveAlbum.setOnClickListener {
            createAlbum()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun createAlbum() {
        val releaseDate = datePickerReleaseDate.year.toString()+"-"+(datePickerReleaseDate.month+1).toString()+"-"+datePickerReleaseDate.dayOfMonth.toString()+"T00:00:00-00:00"
        val album  = Album(null, editTextAlumName.text.toString(), editTextCoverURL.text.toString(), releaseDate,
            editNewAlbumDescription.text.toString(), genreSelected, recordLabelSelected)
        viewModel.createNewAlbum(album)

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[CreateAlbumViewModel::class.java]
        viewModel.getCreateNewAlbumObserver().observe(this, Observer <Album?>{

            if(it  == null) {
                Toast.makeText(this@CreateAlbumActivity, "Failed to create Album", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@CreateAlbumActivity, "Successfully created Album", Toast.LENGTH_LONG).show()
            }
        })
    }
}