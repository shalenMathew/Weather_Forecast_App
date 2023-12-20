package com.example.weatherforecastapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecastapp.domain.location.LocationTracker
import com.example.weatherforecastapp.domain.repository.WeatherRepository
import com.example.weatherforecastapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository,private val locationTracker: LocationTracker):ViewModel()
{


    // in view model we will map the data to the state
    // for purpose of clean architecture the state should have as less knowledge as possible about the logic and should only care about the data than the
// logic

    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo(){

        viewModelScope.launch {
            // launching a coroutine whose life cycle will be as long as the view-model

            state = state.copy(
                isLoading = true,
                error = null
            )

            locationTracker.getCurrentLocation()?.let {

                // let is type of scope used to avoid null pointer exceptions
                // so this will work only if location is not null

              when ( val result = weatherRepository.getWeatherData(it.latitude,it.longitude)){

                  is Resource.Success ->{
                      state = state.copy(weatherInfo = result.data,isLoading = false,error = null)
                  }

                  is Resource.Error->{
                      state=state.copy(weatherInfo =null,isLoading = false,error = result.message)
                  }
              }


            }?:kotlin.run {

                state = state.copy(weatherInfo = null,
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS.")

            }

        }

    }





}