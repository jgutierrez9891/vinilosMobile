package com.example.android.vinylsappg21.ui.albums

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android.vinylsappg21.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import android.widget.Button
import com.example.android.vinylsappg21.databinding.ActivityAlbumDetailBinding
import com.example.android.vinylsappg21.GlobalStuff
import android.view.View


class AlbumDetailActivity : AppCompatActivity() {
    private lateinit var tvAlbumName: TextView
    private lateinit var tvReleaseDate: TextView
    private lateinit var tvGenre: TextView
    private lateinit var tvRecordLabel: TextView
    private lateinit var tvDescription: TextView
    private lateinit var ivCover: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)

        tvAlbumName= findViewById(R.id.tvAlbumName)
        tvAlbumName.text = intent.getStringExtra("album_name")

        tvReleaseDate= findViewById(R.id.tvReleaseDateText)
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000'Z'")
        val formatter = SimpleDateFormat("yyyy/MM/dd")
        tvReleaseDate.text = formatter.format(parser.parse(intent.getStringExtra("release_date")))

        tvGenre= findViewById(R.id.tvGenreText)
        tvGenre.text = intent.getStringExtra("genre")

        tvRecordLabel= findViewById(R.id.tvRecordLabelText)
        tvRecordLabel.text = intent.getStringExtra("record_label")

        tvDescription= findViewById(R.id.tvDescriptionText)
        tvDescription.text = intent.getStringExtra("description")

        ivCover = findViewById(R.id.ivAlbumCover)
        Picasso.get().load(intent.getStringExtra("cover_url")).noFade().into(ivCover, object : Callback {
            override fun onSuccess() {
                ivCover.animate().setDuration(300).alpha(1f).start()
            }
            override fun onError(e: Exception) {
            }
        })

        // showing the back button in action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.drawable.side_nav_bar, null))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}