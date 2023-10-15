package com.khaled_sho.testmedicalapp.main.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.khaled_sho.testmedicalapp.ui.theme.myPrimaryColor

@Composable
fun ShowGreetingText(
    modifier: Modifier = Modifier,
    userName: String
) {
    Text(
        modifier = modifier
            .clip(shape = RoundedCornerShape(9.dp))
            .background(color = Color.White)
            .border(color = myPrimaryColor, width = 2.dp, shape = RoundedCornerShape(9.dp))
            .padding(14.dp),
        text = "Welcome $userName",
        style = TextStyle(
            color = myPrimaryColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.W800
        )
    )
}