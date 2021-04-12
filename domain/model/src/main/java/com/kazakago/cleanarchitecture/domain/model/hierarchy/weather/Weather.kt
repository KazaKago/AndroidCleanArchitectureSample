package com.kazakago.cleanarchitecture.domain.model.hierarchy.weather

import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.CityId
import java.io.Serializable
import java.net.URL
import java.time.LocalDateTime

data class Weather(
    val cityId: CityId,
    val location: Location,
    val title: String,
    val link: URL,
    val publicTime: LocalDateTime,
    val description: Description,
    val forecasts: List<Forecast>
) : Serializable
