package com.kazakago.cleanarchitecture.presentation.hierarchy.city

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class CityListActivityViewModel(application: Application) : AndroidViewModel(application) {

    class Factory(private val application: Application): ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CityListActivityViewModel(application) as T
        }
    }

    var listener: CityListActivityViewModelListener? = null

    fun onClickAboutMenu() {
        listener?.toAboutActivity()
    }

}
