package com.kazakago.cleanarchitecture.domain.model.weather

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Link Model
 *
 * Created by tamura_k on 2016/06/03.
 */
@PaperParcel
data class LinkModel(
        //市区町村名
        val name: String,
        //対応するlivedoor 天気情報のURL
        val link: String
) : PaperParcelable {

    companion object {
        @JvmField
        val CREATOR = PaperParcelLinkModel.CREATOR
    }

}
