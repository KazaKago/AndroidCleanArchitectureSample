package com.kazakago.cleanarchitecture.domain.model.weather

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

/**
 * Forecasts Model
 *
 * Created by tamura_k on 2016/06/03.
 */
@PaperParcel
class ForecastModel : Parcelable {

    //予報日
    var date: String? = null
    //予報日(今日、明日、明後日のいずれか)
    var dateLabel: String? = null
    //天気（晴れ、曇り、雨など）
    var telop: String? = null
    //画像
    var image: ImageModel? = null
    //気温
    var temperature: TemperatureModel? = null

    companion object {
        @JvmField
        val CREATOR = PaperParcelForecastModel.CREATOR
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelForecastModel.writeToParcel(this, dest, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

}
