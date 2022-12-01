package com.example.android.vinylsappg21.ui.tracks

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.vinylsappg21.R
import com.example.android.vinylsappg21.models.Track
import com.example.android.vinylsappg21.viewmodels.CreateTrackViewModel
import kotlinx.android.synthetic.main.activity_create_track.*
import java.util.*

class CreateTrackActivity : AppCompatActivity() {
    lateinit var viewModel: CreateTrackViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_track)

        initViewModel()

        // showing the back button in action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.drawable.side_nav_bar, null))

        btnNewAlbumSaveTrack.setOnClickListener {
            createTrack()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun createTrack() {


        if(editTextTrackName.text.isNullOrBlank() || editTextTrackMinutes.text.isNullOrBlank() || editTextTrackSeconds.text.isNullOrBlank() ){
            Toast.makeText(this@CreateTrackActivity, getString(R.string.fill_all_fields), Toast.LENGTH_LONG).show()
        }else{
            val track  = Track(null, editTextTrackName.text.toString(), editTextTrackMinutes.text.toString()+":"+editTextTrackSeconds.text.toString())
            viewModel.createNewTrack(track)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[CreateTrackViewModel::class.java]
        viewModel.getCreateNewTrackObserver().observe(this, Observer <Track?>{

            if(it  == null) {
                Toast.makeText(this@CreateTrackActivity, getString(R.string.create_track_fail), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@CreateTrackActivity, getString(R.string.create_track_succes), Toast.LENGTH_LONG).show()
                editTextTrackName.text.clear()
                editTextTrackMinutes.text.clear()
                editTextTrackSeconds.text.clear()
            }
        })
    }
}