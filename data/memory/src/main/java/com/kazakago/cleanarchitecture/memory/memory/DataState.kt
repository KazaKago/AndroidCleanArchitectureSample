package com.kazakago.cleanarchitecture.memory.memory

sealed class DataState {
    object Fixed : DataState()
    object Loading : DataState()
    data class Error(val exception: Exception) : DataState()
}
