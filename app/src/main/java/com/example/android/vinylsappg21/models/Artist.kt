package com.example.android.vinylsappg21.models

data class Artist(
    val artistId:Int,
    val name:String,
    val image:String,
    val description:String,
    val birthday:String,
    val albums: Array<String>
)
