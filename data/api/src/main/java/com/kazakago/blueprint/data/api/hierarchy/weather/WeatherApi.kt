package com.kazakago.blueprint.data.api.hierarchy.weather

import android.content.Context
import com.kazakago.blueprint.data.api.global.RetrofitBuilder
import com.kazakago.blueprint.data.api.response.weather.WeatherResponse
import java.net.URL

class WeatherApi(context: Context) {

    private val apiService = RetrofitBuilder(context, URL("http://weather.livedoor.com/forecast/webservice/"))
        .build()
        .create(WeatherService::class.java)

    suspend fun fetch(cityId: String): WeatherResponse {
        return apiService.fetch(cityId).body()!!
    }
}
