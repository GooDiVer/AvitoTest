package com.example.avitotest3

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import javax.security.auth.callback.Callback

interface AvitoInterface {
    @GET("/avito-tech/android-trainee-task/master/pins.json")
    fun getMarkers(): Call<MarkersOnMap>
}