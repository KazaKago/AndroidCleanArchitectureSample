package com.kazakago.cleanarchitecture.presentation.hierarchy.forecast

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.model.weather.Forecast
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.domain.usecase.weather.GetWeatherUseCase
import com.kazakago.cleanarchitecture.presentation.livedata.NoValueSingleLiveEvent
import com.kazakago.cleanarchitecture.presentation.livedata.SingleLiveEvent
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class ForecastViewModel(application: Application,
                        private val getWeatherUseCase: GetWeatherUseCase,
                        private val city: City) : AndroidViewModel(application), ForecastRecyclerAdapter.Listener {

    val title = MutableLiveData<CharSequence>()
    val finish = NoValueSingleLiveEvent()
    val weather = MutableLiveData<Weather>()
    val isLoading = MutableLiveData<Boolean>()
    val showToast = SingleLiveEvent<String>()

    init {
        title.value = city.name
        fetchWeather()
    }

    fun onClickBackIcon() {
        finish.call()
    }

    private fun fetchWeather() = launch(UI) {
        isLoading.value = true
        try {
            weather.value = async { getWeatherUseCase.execute(city.id) }.await()
        } catch (exception: Exception) {
            weather.value = null
            showToast.call(exception.localizedMessage)
        }
        isLoading.value = false
    }


    //region ForecastRecyclerAdapter.Listener

    override fun onItemClick(forecast: Forecast) {
        showToast.call(forecast.telop)
    }

    //endregion

}
