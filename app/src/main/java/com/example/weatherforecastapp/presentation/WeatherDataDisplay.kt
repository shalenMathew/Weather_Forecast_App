package com.example.weatherforecastapp.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp



@Composable
fun WeatherDataDisplay(
value:Int,
unit:String,
icon:ImageVector,
modifier: Modifier=Modifier,
iconTint:Color = Color.White
) {

    Row {

        Icon(imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = modifier
                .padding(2.dp)
                .size(20.dp))
        
        Spacer(modifier = Modifier.width(4.dp))
        
        Text(text = "$value $unit", color = Color.White)
    }

}