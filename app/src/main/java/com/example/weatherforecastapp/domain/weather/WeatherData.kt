package com.example.weatherforecastapp.domain.weather

import java.time.LocalDateTime
import java.util.Date


data class WeatherData (
    val time:Date,
    val temperatureCelsius:Double,
    val pressure:Double,
    val windSpeed:Double,
    val humidity:Double,
    val weatherType: WeatherType

    // more simpler version of WeatherDataDto where we were getting List of lot of things at a once here we are taking all data one at a time
)