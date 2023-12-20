package com.example.weatherforecastapp.presentation

import com.example.weatherforecastapp.domain.weather.WeatherInfo

data class WeatherState(val weatherInfo: WeatherInfo? = null,  // weatherInfo is a pack of data where all our data is stored it, it has data of weather per hour and
// weather per day
                        val isLoading: Boolean = false,
                        val error: String? = null)
