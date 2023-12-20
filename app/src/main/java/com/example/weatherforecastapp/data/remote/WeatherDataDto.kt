package com.example.weatherforecastapp.data.remote

import com.squareup.moshi.Json

data class WeatherDataDto(

    val time:List<String>,

    @field:Json(name = "temperature_2m")
    val temperatures:List<Double>,

    @field:Json(name = "weathercode")
    val weatherCodes:List<Int>,

    @field:Json(name = "relativehumidity_2m")
    val relativeHumidity:List<Double>,

    @field:Json(name = "windspeed_10m")
    val windSpeed:List<Double>,

    @field:Json(name = "pressure_msl")
    val pressures: List<Double>,

)


//	hourly		{6}
//	time		[168]
//	temperature_2m		[168]
//	weathercode		[168]
//	relativehumidity_2m		[168]
//	windspeed_10m		[168]
//	pressure_msl		[168]