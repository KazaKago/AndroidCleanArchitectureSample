package com.kazakago.cleanarchitecture.data.entity.weather

import io.realm.RealmList
import io.realm.RealmObject

open class CopyrightEntity(
        //コピーライトの文言
        var title: String = "",
        //livedoor 天気情報のURL
        var link: String = "",
        //livedoor 天気情報へのURL、アイコンなど
        var image: ImageEntity? = null,
        //livedoor 天気情報で使用している気象データの配信元
        var provider: RealmList<LinkEntity> = RealmList()
) : RealmObject()
