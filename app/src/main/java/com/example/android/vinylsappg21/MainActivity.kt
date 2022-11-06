package com.example.android.vinylsappg21

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val visitor1: ImageView = findViewById(R.id.visitor1)

        val visitor2: TextView = findViewById(R.id.visitor2)

        val collector1: ImageView = findViewById(R.id.collector1)

        val collector2: TextView = findViewById(R.id.collector2)

        visitor1.setOnClickListener {
            val intent = Intent(this, AlbumsActivity::class.java)
            GlobalStuff.userType = 0
            // start your next activity
            startActivity(intent)
        }

        visitor2.setOnClickListener {
            val intent = Intent(this, AlbumsActivity::class.java)
            GlobalStuff.userType = 0
            // start your next activity
            startActivity(intent)
        }

        collector1.setOnClickListener {
            val intent = Intent(this, AlbumsActivity::class.java)
            GlobalStuff.userType = 1
            // start your next activity
            startActivity(intent)
        }

        collector2.setOnClickListener {
            val intent = Intent(this, AlbumsActivity::class.java)
            GlobalStuff.userType = 1
            // start your next activity
            startActivity(intent)
        }



    }
}