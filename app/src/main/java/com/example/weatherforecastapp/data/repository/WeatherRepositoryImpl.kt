package com.example.weatherforecastapp.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.weatherforecastapp.data.mappers.WeatherInfo
import com.example.weatherforecastapp.data.remote.WeatherApi
import com.example.weatherforecastapp.domain.repository.WeatherRepository
import com.example.weatherforecastapp.domain.util.Resource
import com.example.weatherforecastapp.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor ( private val api:WeatherApi):WeatherRepository {

    // it will create the instance if it knows how to make the instance of api

    // this is repository where will call api foe the data and all that


    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {

        return try{
            Resource.Success(api.getWeatherData(lat, long).WeatherInfo())  // calling api and mapping the data from api using WeatherMappers class logic
            // which in the end will return Weather info that have the data of weather forecast data per hour  and weather data forecast per day

        }catch (e:Exception){
            e.printStackTrace()
            Resource.Error(message = e.message?:"An error occurred to some unknown reason")
            // e.message if there there is an error while fetching the data or if the data is null which means error occurred due to some unknown reason
        }

    }
}