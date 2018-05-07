package com.kazakago.cleanarchitecture.web.repository.weather

import android.content.Context
import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherApiRepository
import com.kazakago.cleanarchitecture.web.api.weather.WeatherRetrofit
import com.kazakago.cleanarchitecture.web.response.mapper.weather.WeatherResponseMapper

class WeatherApiRepositoryImpl(private val context: Context) : WeatherApiRepository {

    override fun fetch(cityId: CityId): Weather {
        val weatherApi = WeatherRetrofit.getWeatherApi(context)
        val weatherApiResponse = weatherApi[cityId.value].execute()
        return WeatherResponseMapper.map(weatherApiResponse.body()!!)
    }

}
