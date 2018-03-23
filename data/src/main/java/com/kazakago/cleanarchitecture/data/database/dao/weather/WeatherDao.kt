package com.kazakago.cleanarchitecture.data.database.dao.weather

import android.arch.persistence.room.*
import com.kazakago.cleanarchitecture.data.database.entity.weather.DescriptionEntity
import com.kazakago.cleanarchitecture.data.database.entity.weather.ForecastEntity
import com.kazakago.cleanarchitecture.data.database.entity.weather.LocationEntity
import com.kazakago.cleanarchitecture.data.database.entity.weather.WeatherEntity
import io.reactivex.Single

@Dao
interface WeatherDao {

    @Query("SELECT * FROM Weather WHERE city_id == (:cityId) LIMIT 1")
    fun findWeather(cityId: String): Single<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weather: WeatherEntity)

    @Delete
    fun delete(weather: WeatherEntity)

    @Query("SELECT * FROM Location WHERE city_id == (:cityId) LIMIT 1")
    fun findLocation(cityId: String): Single<LocationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(location: LocationEntity)

    @Delete
    fun delete(location: LocationEntity)

    @Query("SELECT * FROM Description WHERE city_id == (:cityId) LIMIT 1")
    fun findDescription(cityId: String): Single<DescriptionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(description: DescriptionEntity)

    @Delete
    fun delete(description: DescriptionEntity)

    @Query("SELECT * FROM Forecast WHERE city_id == (:cityId)")
    fun findForecasts(cityId: String): Single<List<ForecastEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forecasts: List<ForecastEntity>)

    @Delete
    fun delete(forecasts: List<ForecastEntity>)

}
