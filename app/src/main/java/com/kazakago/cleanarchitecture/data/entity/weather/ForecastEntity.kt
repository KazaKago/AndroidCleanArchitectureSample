package com.kazakago.cleanarchitecture.data.entity.weather

import io.realm.RealmObject

open class ForecastEntity : RealmObject() {

    //予報日
    var date: String? = null
    //予報日(今日、明日、明後日のいずれか)
    var dateLabel: String? = null
    //天気（晴れ、曇り、雨など）
    var telop: String? = null
    //画像
    var image = ImageEntity()
    //気温
    var temperature = TemperatureEntity()

}
