package com.khaled_sho.testmedicalapp.main.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.khaled_sho.testmedicalapp.main.data.model.AssociatedDrug

@Composable
fun DrugList(
    modifier: Modifier,
    list: List<AssociatedDrug>
) {
    LazyColumn {
        itemsIndexed(list) { index, drug ->
            ItemDrugCard(drug, onItemClicked = {})
        }
    }
}