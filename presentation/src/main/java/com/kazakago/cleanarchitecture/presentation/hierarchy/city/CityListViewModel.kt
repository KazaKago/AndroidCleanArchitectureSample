package com.kazakago.cleanarchitecture.presentation.hierarchy.city

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCase
import com.kazakago.cleanarchitecture.presentation.livedata.liveevent.UnitLiveEvent
import com.kazakago.cleanarchitecture.presentation.livedata.nonnulllivedata.NonNullLiveData
import com.kazakago.cleanarchitecture.presentation.livedata.nonnulllivedata.NonNullLiveEvent
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class CityListViewModel(application: Application,
                        private val getCityUseCase: GetCityUseCase) : AndroidViewModel(application) {

    val toAbout = UnitLiveEvent()
    val cityList = NonNullLiveData<List<City>>(emptyList())
    val showToast = NonNullLiveEvent<String>()
    val toForecast = NonNullLiveEvent<City>()

    init {
        fetchCityList()
    }

    fun onClickAboutMenu() {
        toAbout.call()
    }

    fun onClickCity(city: City) {
        toForecast.call(city)
    }

    private fun fetchCityList() = GlobalScope.launch(Dispatchers.Main) {
        try {
            cityList.value = async(Dispatchers.Default) { getCityUseCase.execute(Unit) }.await()
        } catch (exception: Exception) {
            cityList.value = listOf()
            showToast.call(exception.localizedMessage)
        }
    }

}
