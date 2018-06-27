package com.kazakago.cleanarchitecture.presentation.hierarchy.city

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCase
import com.kazakago.cleanarchitecture.presentation.livedata.nonnulllivedata.NonNullLiveData
import com.kazakago.cleanarchitecture.presentation.livedata.nonnulllivedata.NonNullLiveEvent
import com.kazakago.cleanarchitecture.presentation.livedata.liveevent.UnitLiveEvent
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class CityListViewModel(application: Application,
                        private val getCityUseCase: GetCityUseCase) : AndroidViewModel(application), CityRecyclerAdapter.Listener {

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

    private fun fetchCityList() = launch(UI) {
        try {
            cityList.value = async { getCityUseCase.execute(Unit) }.await()
        } catch (exception: Exception) {
            cityList.value = listOf()
            showToast.call(exception.localizedMessage)
        }
    }

    //region CityRecyclerAdapter.Listener

    override fun onItemClick(city: City) {
        toForecast.call(city)
    }

    //endregion

}
