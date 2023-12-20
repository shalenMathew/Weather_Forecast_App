package com.example.weatherforecastapp.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherforecastapp.R
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.TimeZone
import kotlin.math.roundToInt



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherCard(state:WeatherState,
                backgroundColor:Color,
                modifier: Modifier=Modifier){
    
    state.weatherInfo?.currentWeatherData?.let {data ->

        Card(modifier = modifier.padding(15.dp),
            shape = RoundedCornerShape(10.dp)) {
            
            Column( modifier= modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(12.dp)) {

                val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
                val formattedTime: String = sdf.format(data.time)
                
                Text(  text = "Today $formattedTime"
                    , modifier=modifier.align(Alignment.End).padding(end=5.dp), color = Color.White)

                Spacer(modifier = Modifier.height(16.dp))

                Image(painter = painterResource(id = data.weatherType.iconRes),
                    contentDescription = null,
                    modifier = modifier
                        .width(200.dp)
                        .align(Alignment.CenterHorizontally))
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Text(text = "${data.temperatureCelsius}°C",
                    color = Color.White,
                    fontSize = 35.sp,
                    modifier =Modifier.align(Alignment.CenterHorizontally) )

                Spacer(modifier = Modifier.height(12.dp))

                Text(text = data.weatherType.weatherDesc,
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier =Modifier.align(Alignment.CenterHorizontally) )

                Spacer(modifier = Modifier.height(15.dp))

                Row(modifier= modifier
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround)
                {

                    WeatherDataDisplay(value = data.pressure.roundToInt(), unit = "hpa", icon = ImageVector.vectorResource(id = R.drawable.ic_pressure) )

                    WeatherDataDisplay(value = data.humidity.roundToInt(), unit = "%", icon = ImageVector.vectorResource(id = R.drawable.ic_drop) )

                    WeatherDataDisplay(value = data.windSpeed.roundToInt(), unit = "km/hr", icon = ImageVector.vectorResource(id = R.drawable.ic_wind) )
                    
                }
            }
        }


    }




}