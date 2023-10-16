package com.khaled_sho.testmedicalapp.main.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.khaled_sho.testmedicalapp.R
import com.khaled_sho.testmedicalapp.core.util.ifEmptyOrNull
import com.khaled_sho.testmedicalapp.main.data.model.AssociatedDrug
import com.khaled_sho.testmedicalapp.ui.theme.myPrimaryColor

@Composable
fun ItemDrugCard(
    index: Int,
    drug: AssociatedDrug,
    onItemClicked: (drug: AssociatedDrug, imageRes: Int) -> Unit
) {
    val imageRes = if (index % 2 == 0) R.drawable.drugs else R.drawable.medicine
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.White)
            .border(width = 2.dp, color = myPrimaryColor, shape = RoundedCornerShape(16.dp))
            .clickable(onClick = { onItemClicked(drug, imageRes) }),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Gray
        )
    ) {
        val modifier: Modifier = Modifier
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                val name = drug.name?.ifEmptyOrNull("drug name (Default Value)")
                val strength = drug.strength?.ifEmptyOrNull("drug strength (Default Value)")
                val dose = drug.dose?.ifEmptyOrNull("drug dose (Default Value)")
                drug.name = name
                drug.strength = strength
                drug.dose = dose
                Text(
                    text = "Name: $name",
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = "Strength: $strength",
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    color = Color.White,
                    style = typography.titleLarge
                )
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = "Dose: $dose",
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    color = Color.White,
                    style = typography.titleSmall
                )
            }

            Image(
                painterResource(imageRes),
                contentDescription = "DrugImage",
                contentScale = ContentScale.Fit,
                modifier = modifier.height(90.dp)
            )
        }
    }
}