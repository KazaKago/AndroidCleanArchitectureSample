package com.kazakago.cleanarchitecture.di.module

import android.content.Context
import com.kazakago.cleanarchitecture.data.repository.AboutRepositoryImpl
import com.kazakago.cleanarchitecture.data.repository.CityRepositoryImpl
import com.kazakago.cleanarchitecture.data.repository.WeatherRepositoryImpl
import com.kazakago.cleanarchitecture.domain.repository.AboutRepository
import com.kazakago.cleanarchitecture.domain.repository.CityRepository
import com.kazakago.cleanarchitecture.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

//    @Singleton
//    @Provides
//    fun provideOrmaDatabase(context: Context): OrmaDatabase {
//        return OrmaDatabase.builder(context)
//                .build()
//    }

    @Provides
    @Singleton
    fun provideCityRepository(context: Context): CityRepository {
        return CityRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(): WeatherRepository {
        return WeatherRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideAboutRepository(context: Context): AboutRepository {
        return AboutRepositoryImpl(context)
    }

}