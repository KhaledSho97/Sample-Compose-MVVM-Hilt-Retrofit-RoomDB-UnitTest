package com.khaled_sho.testmedicalapp.main.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainHeader(modifier: Modifier = Modifier, userName: String) {
    Column(
        modifier = modifier.fillMaxWidth().padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ShowGreetingText(modifier = modifier, userName)
        TimeText()
        DateText()
    }
}