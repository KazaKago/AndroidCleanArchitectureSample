package com.kazakago.cleanarchitecture.data.repository.weather

import android.content.Context
import com.kazakago.cleanarchitecture.data.database.dao.AppDatabase
import com.kazakago.cleanarchitecture.data.database.mapper.weather.WeatherEntityMapper
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherRepository

class WeatherRepositoryImpl(private val context: Context) : WeatherRepository {

    private val database = AppDatabase.create(context)

    override fun find(cityId: String): Weather? {
        val weatherDao = database.weatherDao()
        val weather = weatherDao.findWeather(cityId)
        val location = weatherDao.findLocation(cityId)
        val description = weatherDao.findDescription(cityId)
        val forecasts = weatherDao.findForecasts(cityId)
        return WeatherEntityMapper.map(weather, location, description, forecasts)
    }

    override fun insert(weather: Weather) {
        val reverseMappingResult = WeatherEntityMapper.reverse(weather)
        val weatherDao = database.weatherDao()
        weatherDao.insert(reverseMappingResult.weatherEntity)
        weatherDao.insert(reverseMappingResult.locationEntity)
        weatherDao.insert(reverseMappingResult.descriptionEntity)
        weatherDao.insert(reverseMappingResult.forecastEntities)
    }

    override fun delete(weather: Weather) {
        val reverseMappingResult = WeatherEntityMapper.reverse(weather)
        val weatherDao = database.weatherDao()
        weatherDao.delete(reverseMappingResult.weatherEntity)
        weatherDao.delete(reverseMappingResult.locationEntity)
        weatherDao.delete(reverseMappingResult.descriptionEntity)
        weatherDao.delete(reverseMappingResult.forecastEntities)
    }

}
