package com.khaled_sho.testmedicalapp.main.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.khaled_sho.testmedicalapp.main.data.model.AssociatedDrug
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
@Composable
fun ShowList(
    navController: NavController,
    modifier: Modifier = Modifier,
    listOfAssociatedDrug: List<AssociatedDrug>
) {
    LazyColumn(
        modifier = modifier.background(color = Color.White)
    ) {
        itemsIndexed(listOfAssociatedDrug) { _, drug ->
            ItemDrugCard(drug, onItemClicked = {
                val sdf =
                    SimpleDateFormat("'Date\n'dd-MM-yyyy '\n\nand\n\nTime\n'HH:mm:ss z")
                val currentDateAndTime = sdf.format(Date()).toString()
                navController.navigate("ProfileDetails/khaled/1/$currentDateAndTime")
            })
        }
    }
}