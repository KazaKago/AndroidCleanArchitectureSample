package com.kazakago.cleanarchitecture.data.repository.mapper.weather

import com.kazakago.cleanarchitecture.data.api.entity.weather.DescriptionResponse
import com.kazakago.cleanarchitecture.data.repository.extension.parseDateTime
import com.kazakago.cleanarchitecture.domain.model.weather.Description

internal class DescriptionResponseMapper {

    fun map(source: DescriptionResponse): Description {
        return Description(
            text = source.text,
            publicTime = source.publicTime.parseDateTime()
        )
    }

}
