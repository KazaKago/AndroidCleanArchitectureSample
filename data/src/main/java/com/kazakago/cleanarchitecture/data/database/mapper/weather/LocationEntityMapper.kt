package com.kazakago.cleanarchitecture.data.database.mapper.weather

import com.kazakago.cleanarchitecture.data.database.entity.weather.LocationEntity
import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.domain.model.weather.Location

object LocationEntityMapper {

    fun map(source: LocationEntity): Location {
        return Location(
                area = source.area,
                prefecture = source.prefecture,
                city = source.city)
    }

    fun reverse(cityId: CityId, destination: Location): LocationEntity {
        return LocationEntity(
                cityId = cityId.value,
                area = destination.area,
                prefecture = destination.prefecture,
                city = destination.city)
    }

}
