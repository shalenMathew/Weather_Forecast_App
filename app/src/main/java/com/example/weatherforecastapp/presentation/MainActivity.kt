package com.example.weatherforecastapp.presentation

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherforecastapp.ui.theme.DarkBlue
import com.example.weatherforecastapp.ui.theme.DeepBlue
import com.example.weatherforecastapp.ui.theme.WeatherForecastAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // https://api.open-meteo.com/v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl&latitude=52.52&longitude=13.41

    private val weatherViewModel:WeatherViewModel by viewModels()

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            weatherViewModel.loadWeatherInfo()
        }

        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ))

        setContent {
            WeatherForecastAppTheme {
                Box(modifier = Modifier.fillMaxSize()){

                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(DarkBlue), verticalArrangement = Arrangement.Center)
                    {
                        WeatherCard(state = weatherViewModel.state, backgroundColor = DeepBlue)
                        Spacer(modifier = Modifier.height(20.dp))
                        WeatherForecast(state =weatherViewModel.state )
                    }

                    if (weatherViewModel.state.isLoading){
                        CircularProgressIndicator(modifier=Modifier.align(Alignment.Center))
                    }
                    weatherViewModel.state.error?.let { 
                        Text(text = it, color = Color.Red,modifier=Modifier.align(Alignment.Center), fontSize = 20.sp)
                    }
                }
            }
        }
    }
}

