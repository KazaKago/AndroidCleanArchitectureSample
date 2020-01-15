package com.kazakago.cleanarchitecture.data.api.api.weather

import android.content.Context
import com.kazakago.cleanarchitecture.data.api.api.RetrofitBuilder
import com.kazakago.cleanarchitecture.data.api.entity.weather.WeatherResponse
import java.net.URL

class WeatherApi(context: Context) {

    private val apiService = RetrofitBuilder(context, URL("http://weather.livedoor.com/forecast/webservice/"))
        .build()
        .create(WeatherService::class.java)

    suspend fun fetch(cityId: String): WeatherResponse {
        return apiService.fetch(cityId).body()!!
    }

}
