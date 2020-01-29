package com.example.avitotest3

class AvitoRepository  {
    val client = RetrofitClient.webService

    suspend fun getMarkers() = client.getMarkers()
}