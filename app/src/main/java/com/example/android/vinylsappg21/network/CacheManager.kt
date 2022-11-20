package com.example.android.vinylsappg21.network
import android.content.Context
import com.example.android.vinylsappg21.models.Collector

class CacheManager(context: Context) {
    companion object{
        var instance: CacheManager? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CacheManager(context).also {
                    instance = it
                }
            }
    }
    private var collectors: List<Collector> = listOf()
    fun addCollectors(collectorList: List<Collector>){
        if (collectors.isEmpty() || collectorList.size != collectors.size){
                collectors = collectorList
        }
    }
    fun getCollectors() : List<Collector>{
        return if (collectors.isNotEmpty()) collectors!! else listOf<Collector>()
    }
}