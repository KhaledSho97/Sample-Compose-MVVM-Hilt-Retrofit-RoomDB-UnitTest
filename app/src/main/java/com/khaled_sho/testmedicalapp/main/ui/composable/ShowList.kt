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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.khaled_sho.testmedicalapp.R
import com.khaled_sho.testmedicalapp.main.data.model.AssociatedDrug
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
@Composable
fun ShowList(
    navController: NavController,
    listOfAssociatedDrug: List<AssociatedDrug>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.background(color = Color.White).padding(0.dp, 10.dp)) {
        Text(modifier = modifier.padding(8.dp, 0.dp), text = "Diabetes Associated Drugs List: ")
        LazyColumn(
            modifier = modifier.background(color = Color.White)
                .testTag(stringResource(R.string.test_list_tag))
        ) {
            itemsIndexed(listOfAssociatedDrug) { index, drug ->
                ItemDrugCard(index, drug, onItemClicked = { drugItem, image ->
                    navController.navigate("ProfileDetails/${drugItem.name}/${drugItem.strength}/${drugItem.dose}/${image}")
                })
            }
        }
    }
}