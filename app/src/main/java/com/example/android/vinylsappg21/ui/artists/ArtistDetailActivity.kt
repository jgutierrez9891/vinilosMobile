package com.example.android.vinylsappg21.ui.artists

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android.vinylsappg21.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class ArtistDetailActivity : AppCompatActivity() {

    private lateinit var nameArtist: TextView
    private lateinit var imageArtist: ImageView
    private lateinit var birthdayArtist: TextView
    private lateinit var descArtist: TextView
    private lateinit var albumList : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_detail)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        nameArtist = findViewById(R.id.artists_fragment_name)
        nameArtist.text = intent.getStringExtra("artist_name")

        imageArtist = findViewById(R.id.artist_detail_fragment_img)
        Picasso.get().load(intent.getStringExtra("image_url")).noFade().into(imageArtist, object : Callback {
            override fun onSuccess() {
                imageArtist.animate().setDuration(300).alpha(1f).start()
            }
            override fun onError(e: Exception) {
            }
        })

        birthdayArtist = findViewById(R.id.birthday_artist)
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000'Z'")
        val formatter = SimpleDateFormat("yyyy/MM/dd")
        birthdayArtist.text = formatter.format(parser.parse(intent.getStringExtra("birth_date")))

        descArtist = findViewById(R.id.details_artist)
        descArtist.text = intent.getStringExtra("description")

        albumList = findViewById(R.id.albumsList)
        val list = intent.getStringArrayListExtra("albums")
        list?.let{
            val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, list!!)
            albumList.adapter = arrayAdapter
        }

        // showing the back button in action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}