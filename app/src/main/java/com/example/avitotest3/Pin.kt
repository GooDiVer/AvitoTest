package com.example.avitotest3


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pin(
    @Json(name = "coordinates")
    val coordinates: Coordinates,
    @Json(name = "id")
    val id: Int,
    @Json(name = "service")
    val service: String
)