package com.kazakago.cleanarchitecture.web.response.entity.weather

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class ForecastResponse(
        @Json(name = "date")
        val date: String,
        @Json(name = "dateLabel")
        val dateLabel: String,
        @Json(name = "telop")
        val telop: String,
        @Json(name = "image")
        val image: ImageResponse,
        @Json(name = "temperature")
        val temperature: TemperatureResponse
)