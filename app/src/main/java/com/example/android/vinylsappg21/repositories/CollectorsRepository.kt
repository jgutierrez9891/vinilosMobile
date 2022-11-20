package com.example.android.vinylsappg21.repositories


import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.example.android.vinylsappg21.models.Collector
import com.example.android.vinylsappg21.network.CacheManager
import com.example.android.vinylsappg21.network.NetworkServiceAdapter

class CollectorsRepository (val application: Application){


    suspend fun refreshData(): List<Collector> {
        var potentialResp = CacheManager.getInstance(application.applicationContext).getCollectors()
        if(potentialResp.isEmpty()){
            Log.d("Cache decision", "get from network")
            var collectors = NetworkServiceAdapter.getInstance(application).getCollectors()
            CacheManager.getInstance(application.applicationContext).addCollectors(collectors)
            return collectors
        }
        else{
            Log.d("Cache decision", "return ${potentialResp.size} elements from cache")
            return potentialResp
        }
    }
}