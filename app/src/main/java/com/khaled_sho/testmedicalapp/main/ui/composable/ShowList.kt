package com.khaled_sho.testmedicalapp.main.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
    Column(modifier = modifier.background(color = Color.White).padding(0.dp, 10.dp)) {
        Text(modifier = modifier.padding(8.dp, 0.dp), text = "Diabetes Associated Drugs List: ")
        LazyColumn(
            modifier = modifier.background(color = Color.White)
        ) {
            itemsIndexed(listOfAssociatedDrug) { _, drug ->
                ItemDrugCard(drug, onItemClicked = {
                    navController.navigate("ProfileDetails/${drug.name}/${drug.strength}/${drug.dose}")
                })
            }
        }
    }
}