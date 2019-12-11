package com.kazakago.cleanarchitecture.repository.mapper.weather

import com.kazakago.cleanarchitecture.api.entity.weather.DescriptionResponse
import com.kazakago.cleanarchitecture.model.weather.Description
import com.kazakago.cleanarchitecture.repository.extension.parseDateTime

object DescriptionResponseMapper {

    fun map(source: DescriptionResponse): Description {
        return Description(
            text = source.text,
            publicTime = source.publicTime.parseDateTime()
        )
    }

}
