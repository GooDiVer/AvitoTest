package com.example.avitotest3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelMarkers : ViewModel() {

    private val _markersOnMap = MutableLiveData<MarkersOnMap>()
    val markersOnMap: LiveData<MarkersOnMap>
        get() = _markersOnMap

    fun loadMarkers() {
        val service = RetrofitClient.webService
        service.getMarkers().enqueue(object : Callback<MarkersOnMap> {
            override fun onFailure(call: Call<MarkersOnMap>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<MarkersOnMap>, response: Response<MarkersOnMap>) {
                _markersOnMap.value = response.body()
            }

        })
    }

}