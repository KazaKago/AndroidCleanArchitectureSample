package com.kazakago.cleanarchitecture.repository.mapper.weather

import com.kazakago.cleanarchitecture.api.entity.weather.ForecastResponse
import com.kazakago.cleanarchitecture.model.weather.Forecast
import com.kazakago.cleanarchitecture.repository.extension.parseDate
import com.kazakago.cleanarchitecture.repository.mapper.ResponseMapper
import java.net.URL

object ForecastResponseMapper : ResponseMapper<ForecastResponse, Forecast> {

    override fun map(source: ForecastResponse): Forecast {
        return Forecast(
            telop = source.telop,
            date = source.date.parseDate(),
            dateLabel = source.dateLabel,
            imageUrl = URL(source.image.url),
            maxTemperature = source.temperature.max?.celsius,
            minTemperature = source.temperature.min?.celsius
        )
    }

}
