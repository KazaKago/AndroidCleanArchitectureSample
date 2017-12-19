package com.kazakago.cleanarchitecture.data.file.mapper.city

import com.kazakago.cleanarchitecture.data.file.entity.city.PrefEntity
import com.kazakago.cleanarchitecture.domain.model.city.City

object CityMapper {

    fun map(source: PrefEntity): List<City> {
        val prefTitle = source.title
        return source.cityList.map {
            City(
                    id = it.id,
                    name = prefTitle + " " + it.title)
        }
    }

}
