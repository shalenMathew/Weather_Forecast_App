package com.example.weatherforecastapp.domain.weather

data class WeatherInfo(
    val weatherDataPerDay:Map<Int,List<WeatherData>>,  // this is map tha has the info of all weather forecast of all 7 days , the key here represents the no of days
    val currentWeatherData:WeatherData? // this is the weather info of only today
)