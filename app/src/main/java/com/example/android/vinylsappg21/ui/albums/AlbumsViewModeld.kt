package com.example.android.vinylsappg21.ui.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AlbumsViewModeld : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        //value = "This is albums Fragment"
    }
    val text: LiveData<String> = _text
}