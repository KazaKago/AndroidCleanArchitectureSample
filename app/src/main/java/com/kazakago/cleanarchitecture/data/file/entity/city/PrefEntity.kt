package com.kazakago.cleanarchitecture.data.file.entity.city

import com.squareup.moshi.Json

data class PrefEntity(
        @Json(name = "title")
        val title: String,
        @Json(name = "city")
        val cityList: List<CityEntity>
)