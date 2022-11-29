package com.example.android.vinylsappg21.models

data class Album(
    val albumId:Int?,
    val name:String,
    val cover:String,
    val releaseDate:String,
    val description:String,
    val genre:String,
    val recordLabel:String
)

data class AlbumResponse(val code: Int?, val meta: String?, val data: Album?)