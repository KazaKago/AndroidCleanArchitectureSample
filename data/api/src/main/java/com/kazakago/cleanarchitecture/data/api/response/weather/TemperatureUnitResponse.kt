package com.kazakago.cleanarchitecture.data.api.response.weather

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TemperatureUnitResponse(
    @Json(name = "celsius")
    val celsius: Float,
    @Json(name = "fahrenheit")
    val fahrenheit: Float
)