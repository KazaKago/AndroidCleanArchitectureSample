package com.kazakago.cleanarchitecture.domain.model.city

import java.io.Serializable

data class City(
    val id: CityId,
    val name: String
) : Serializable