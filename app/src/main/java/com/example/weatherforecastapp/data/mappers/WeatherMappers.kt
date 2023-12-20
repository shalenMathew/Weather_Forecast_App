package com.example.weatherforecastapp.data.mappers

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.weatherforecastapp.data.remote.WeatherDataDto
import com.example.weatherforecastapp.data.remote.WeatherDto
import com.example.weatherforecastapp.domain.weather.WeatherData
import com.example.weatherforecastapp.domain.weather.WeatherInfo
import com.example.weatherforecastapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

data class IndexedWeatherData(val index:Int,val weatherData: WeatherData)

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDataDto.toWeatherDataMap():Map<Int,List<WeatherData>>{

 return  time.mapIndexed{index,time->

        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val humidity = relativeHumidity[index]
        val windSpeed = windSpeed[index]
        val pressure = pressures[index]

     val sdfIST = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
     sdfIST.timeZone = TimeZone.getTimeZone("IST") // Set timezone to IST

     val parsedTimeIST: Date = sdfIST.parse(time)!!

     val sdfGMT = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
     sdfGMT.timeZone = TimeZone.getTimeZone("GMT") // Set timezone to GMT

     val timeGMT: String = sdfGMT.format(parsedTimeIST)
     val parsedTimeGMT: Date = sdfGMT.parse(timeGMT)!!

     Log.d("TAG","Parsed time - $parsedTimeGMT")

        IndexedWeatherData(index, weatherData = WeatherData(
            parsedTimeGMT,
            temperature,
            pressure,
            windSpeed,
            humidity,
            WeatherType.fromWMO(weatherCode)))
// we have just simply collected and stored the weather data from a specific hour in IndexedWeatherData() data class
     }.groupBy {
it.index/24
     // mapping the indexed data by using this condition
 }.mapValues {

     it.value.map {
         it.weatherData
     }
 }

}


@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDto.WeatherInfo():WeatherInfo{

    val weatherDataMap = weatherDataDto.toWeatherDataMap()
    val now = Calendar.getInstance(TimeZone.getTimeZone("GMT"))
    //    val now = Calendar.getInstance()

    val currentWeatherData = weatherDataMap[0]?.find {
        // finding current hour
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT")) // Set timezone to GMT
        //       val calendar = Calendar.getInstance()

//        Log.d("TAG2",it.time.toString())

        calendar.time = it.time
        val hour = if (now.get(Calendar.MINUTE) < 30) now.get(Calendar.HOUR_OF_DAY) else now.get(Calendar.HOUR_OF_DAY) + 1
        calendar.get(Calendar.HOUR_OF_DAY) == hour
    }
    return WeatherInfo(weatherDataPerDay = weatherDataMap, currentWeatherData = currentWeatherData)
}