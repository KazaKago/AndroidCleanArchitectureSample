package com.weathercock.android_cleanarchitecture.domain.repository;

import com.weathercock.android_cleanarchitecture.domain.model.weather.WeatherModel;

import rx.Observable;

/**
 * Weather Repository
 *
 * @author Kensuke
 */
public interface WeatherRepository {

    Observable<WeatherModel> fetch(int cityId);

    WeatherModel find(int cityId);

}
