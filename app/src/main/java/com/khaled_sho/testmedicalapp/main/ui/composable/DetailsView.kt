package com.khaled_sho.testmedicalapp.main.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.khaled_sho.testmedicalapp.R
import java.util.Random


@Preview(showBackground = true)
@Composable
fun DetailsView1() {
    DetailsView(
        Modifier,
        "Khaled", "500 ", "Dose", R.drawable.medicine
    )
}


@Composable
fun DetailsView(
    modifier: Modifier = Modifier,
    name: String,
    strength: String,
    dose: String,
    image: Int
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RoundedDrawableImage(image, Color.White)
        Text(
            text = "Name is: $name"
        )
        Text(
            text = "Strength is: $strength"
        )
        Text(
            text = "Dose is:$dose"
        )
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "How much this drug is recommended for you? (Random Value)",
            )
            Spacer(
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.dp_20))
            )
            ShowMyCircularProgress(
                percentage = randFloat(0f, 1f),
                number = 100,
                animationDuration = 3000
            )
        }

    }
}

fun randFloat(min: Float, max: Float): Float {
    val rand = Random()
    return rand.nextFloat() * (max - min) + min
}