package com.example.android.vinylsappg21.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.android.vinylsappg21.models.Album
import com.example.android.vinylsappg21.network.RetroInstance
import com.example.android.vinylsappg21.repositories.RetroServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateAlbumViewModel(application: Application): AndroidViewModel(application) {
    private var createNewAlbumLiveData: MutableLiveData<Album?> = MutableLiveData()

    fun getCreateNewAlbumObserver(): MutableLiveData<Album?> {
        return createNewAlbumLiveData
    }

    fun createNewAlbum(album: Album) {
        val retroService  = RetroInstance.getRetroInstance().create(RetroServiceInterface::class.java)
        val call = retroService.createAlbum(album)
        call.enqueue(object: Callback<Album> {
            override fun onFailure(call: Call<Album>, t: Throwable) {
                createNewAlbumLiveData.postValue(null)
            }

            override fun onResponse(call: Call<Album>, response: Response<Album>) {
                if(response.isSuccessful) {
                    createNewAlbumLiveData.postValue(response.body())
                } else {
                    createNewAlbumLiveData.postValue(null)
                }
            }
        })
    }
}