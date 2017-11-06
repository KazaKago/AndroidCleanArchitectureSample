package com.kazakago.cleanarchitecture.data.entity.weather

import io.realm.RealmObject

open class TemperatureEntity(
        //最高気温
        var max: TemperatureUnitEntity? = null,
        //最低気温
        var min: TemperatureUnitEntity? = null
) : RealmObject()