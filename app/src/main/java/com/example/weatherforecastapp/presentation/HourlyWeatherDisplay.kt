package com.example.weatherforecastapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherforecastapp.domain.weather.WeatherData
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

@Composable
fun HourlyWeatherDisplay(weatherData:WeatherData,
                         modifier: Modifier=Modifier,
                         color: Color=Color.White){


    val formattedTime = remember(weatherData) {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("GMT") // Set timezone to GMT
        sdf.format(weatherData.time)
    }

    Column(modifier= modifier.padding(10.dp).height(100.dp), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween) {

        Text(text = formattedTime,color=color, fontSize = 12.sp)

        Image(
            painter = painterResource(id = weatherData.weatherType.iconRes),
            contentDescription = null,
            modifier = Modifier.width(40.dp)
        )

        Text(
            text = "${weatherData.temperatureCelsius}°C",
            color = color,
            fontWeight = FontWeight.Bold
        )

    }


}