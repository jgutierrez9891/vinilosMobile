package com.example.android.vinylsappg21.models

data class Track(
    val trackId:Int?,
    val name:String,
    val duration:String
)

data class TrackResponse(val code: Int?, val meta: String?, val data: Track?)