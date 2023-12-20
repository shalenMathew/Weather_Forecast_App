package com.example.weatherforecastapp.domain.repository

import com.example.weatherforecastapp.domain.util.Resource
import com.example.weatherforecastapp.domain.weather.WeatherInfo

interface WeatherRepository {

    suspend fun getWeatherData(lat:Double,long:Double):Resource<WeatherInfo> // passing the the data of weather per hour and day  stored in the
// mappers to ui

}