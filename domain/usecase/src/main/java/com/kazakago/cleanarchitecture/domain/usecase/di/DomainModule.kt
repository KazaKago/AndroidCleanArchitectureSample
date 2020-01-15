package com.kazakago.cleanarchitecture.domain.usecase.di

import com.kazakago.cleanarchitecture.domain.usecase.usecase.about.SubscribeAboutUseCase
import com.kazakago.cleanarchitecture.domain.usecase.usecase.about.SubscribeAboutUseCaseImpl
import com.kazakago.cleanarchitecture.domain.usecase.usecase.city.SubscribeCityListUseCase
import com.kazakago.cleanarchitecture.domain.usecase.usecase.city.SubscribeCityListUseCaseImpl
import com.kazakago.cleanarchitecture.domain.usecase.usecase.weather.RequestWeatherUseCase
import com.kazakago.cleanarchitecture.domain.usecase.usecase.weather.RequestWeatherUseCaseImpl
import com.kazakago.cleanarchitecture.domain.usecase.usecase.weather.SubscribeWeatherUseCase
import com.kazakago.cleanarchitecture.domain.usecase.usecase.weather.SubscribeWeatherUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    single<SubscribeWeatherUseCase> { SubscribeWeatherUseCaseImpl(get(), get()) }
    single<RequestWeatherUseCase> { RequestWeatherUseCaseImpl(get()) }
    single<SubscribeCityListUseCase> { SubscribeCityListUseCaseImpl(get()) }
    single<SubscribeAboutUseCase> { SubscribeAboutUseCaseImpl(get()) }
}