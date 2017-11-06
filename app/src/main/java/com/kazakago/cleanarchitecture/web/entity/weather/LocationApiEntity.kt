package com.kazakago.cleanarchitecture.web.entity.weather

data class LocationApiEntity(
        //地方名（例・九州地方）
        val area: String = "",
        //都道府県名（例・福岡県）
        val prefecture: String = "",
        //1次細分区名（例・八幡）
        val city: String = ""
)