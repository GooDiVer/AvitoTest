package com.example.avitotest3


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarkersOnMap(
    @Json(name = "pins")
    val pins: List<Pin>,
    @Json(name = "services")
    val services: List<String>
)