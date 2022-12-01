package com.example.android.vinylsappg21.ui.collectors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.android.vinylsappg21.R
import com.example.android.vinylsappg21.models.Collector

class CollectorDetailActivity : AppCompatActivity() {

    private lateinit var nameCollector: TextView
    private lateinit var telCollector: TextView
    private lateinit var emailCollector: TextView
    private lateinit var albumsList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collector_detail)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        nameCollector = findViewById(R.id.collectors_fragment_name)
        nameCollector.text = intent.getStringExtra("collector_name")

        telCollector = findViewById(R.id.collector_details_telephone)
        telCollector.text = intent.getStringExtra("collector_tel")

        emailCollector = findViewById(R.id.collector_details_email)
        emailCollector.text = intent.getStringExtra("collector_email")

        albumsList = findViewById(R.id.collectorAlbumsList)
        val list = intent.getStringArrayListExtra("collector_albums")
        list?.let{
            val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, list!!)
            albumsList.adapter = arrayAdapter
        }

        // showing the back button in action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}