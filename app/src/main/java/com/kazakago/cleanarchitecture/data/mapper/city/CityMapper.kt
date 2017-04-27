package com.kazakago.cleanarchitecture.data.mapper.city

import com.kazakago.cleanarchitecture.data.entity.city.PrefEntity
import com.kazakago.cleanarchitecture.domain.mapper.EntityMapper
import com.kazakago.cleanarchitecture.domain.model.city.CityModel

/**
 * Created by tamura_k on 2016/12/08.
 */
object CityMapper: EntityMapper<PrefEntity, List<CityModel>>() {

    override fun map(source: PrefEntity): List<CityModel> {
        val prefTitle = source.title
        return source.cityList.map {
            val city = CityModel()
            city.id = it.id
            city.name = prefTitle + " " + it.title
            city
        }
    }

    override fun reverse(destination: List<CityModel>): PrefEntity {
        TODO("not implemented")
    }

}
