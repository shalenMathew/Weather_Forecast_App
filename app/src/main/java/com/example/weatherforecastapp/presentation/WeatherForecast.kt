package com.example.weatherforecastapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherForecast(state: WeatherState,modifier: Modifier=Modifier){

    state.weatherInfo?.weatherDataPerDay?.get(0)?.let {// the list got the weather data of all 7 days we are fetching the weather data of day 0

        Column {
            Text(text = "Today",modifier=modifier.padding(start = 15.dp), fontSize = 25.sp, color = Color.White)

            Spacer(modifier = Modifier.height(14.dp))

            LazyRow(content = {
                items(it){
                    HourlyWeatherDisplay(weatherData = it)
                }
            })

        }

    }
}