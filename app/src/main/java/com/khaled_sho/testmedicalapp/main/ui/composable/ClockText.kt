package com.khaled_sho.testmedicalapp.main.ui.composable

import android.annotation.SuppressLint
import android.widget.TextClock
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.khaled_sho.testmedicalapp.ui.theme.myPrimaryColor
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun DisplayTxtClock() {
    // on below line we are creating a text clock.
    AndroidView(
        // on below line we are initializing our text clock.
        factory = { context ->
            TextClock(context).apply {
                // on below line we are setting 12 hour format.
                format12Hour?.let { this.format12Hour = "hh:mm:ss a" }
                // on below line we are setting time zone.
                timeZone?.let { this.timeZone = it }
                // on below line we are setting text size.
                textSize.let { this.textSize = 21f }
            }
        },
        // on below line we are adding padding.
        modifier = Modifier.padding(5.dp),
    )
}

@Composable
fun TimeText() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "The time now is: ",
            style = TextStyle(
                color = myPrimaryColor,
                fontSize = 19.sp,
                fontWeight = FontWeight.W800
            )
        )
        DisplayTxtClock()
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun DateText() {
    val sdf =
        SimpleDateFormat("dd-MM-yyyy")
    val currentDateAndTime = sdf.format(Date()).toString()
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "And today is: ",
            style = TextStyle(
                color = myPrimaryColor,
                fontSize = 19.sp,
                fontWeight = FontWeight.W800
            )
        )
        Text(
            text = currentDateAndTime,
            style = TextStyle(
                fontSize = 19.sp,
                fontWeight = FontWeight.W800
            )
        )
    }
}