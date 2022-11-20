package com.example.android.vinylsappg21.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.android.vinylsappg21.models.Artist
import com.example.android.vinylsappg21.repositories.ArtistsRepository

class ArtistViewModel(application: Application) :  AndroidViewModel(application) {

    private val _artists = MutableLiveData<List<Artist>>()
    private val _artist = MutableLiveData<Artist>()
    private val artistsRepository = ArtistsRepository(application)

    val artists: LiveData<List<Artist>>
        get() = _artists

    val artist: LiveData<Artist>
        get() = _artist

    private var _eventNetworkError = MutableLiveData(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        artistsRepository.refreshData({
            _artists.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false

        },{
            _eventNetworkError.value = true
        })
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ArtistViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ArtistViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct view-model")
        }
    }
}