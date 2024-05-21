package com.example.labapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Category(navController: NavController) {
    var filteredTrails by remember { mutableStateOf(trails) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = if (DeviceConfig.isTablet()) 40.dp else 24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Buttons for sorting trails
        Row(
            modifier = Modifier.fillMaxWidth().
                padding(24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { filteredTrails = trails.filter { it.difficulty == "easy" } },
                modifier = Modifier.width(if (DeviceConfig.isTablet()) 250.dp else 150.dp)
            ) {
                Text(
                    text = "Łatwe",
                    fontSize = if (DeviceConfig.isTablet()) 32.sp else 20.sp
                )
            }
            Button(
                onClick = { filteredTrails = trails.filter { it.difficulty == "hard" } },
                modifier = Modifier.width(if (DeviceConfig.isTablet()) 250.dp else 150.dp)
            ) {
                Text(
                    text = "Trudne",
                    fontSize = if (DeviceConfig.isTablet()) 32.sp else 20.sp
                )
            }
        }

        // RecyclerView
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(filteredTrails) { trail ->
                TrailCard(trail = trail, onTrailClick = { navigateToTrailDetails(navController, trail) })
                Spacer(modifier = Modifier.height(if (DeviceConfig.isTablet()) 20.dp else 8.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrailCard(trail: Trail, onTrailClick: () -> Unit) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onTrailClick
    ) {
        Column(
            modifier = Modifier.padding(if (DeviceConfig.isTablet()) 42.dp else 16.dp)
        ) {
            Text(
                text = trail.title,
                fontWeight = FontWeight.Bold,
                fontSize = if (DeviceConfig.isTablet()) 44.sp else 24.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                val image = painterResource(id = trail.idImage)
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier
                        .width(if (DeviceConfig.isTablet()) 300.dp else 150.dp)
                        .height(if (DeviceConfig.isTablet()) 200.dp else 100.dp)
                        .clip(RoundedCornerShape(if (DeviceConfig.isTablet()) 16.dp else 8.dp)) // Zaokrąglone narożniki obrazu
                )
                Text(
                    fontSize = if (DeviceConfig.isTablet()) 40.sp else 16.sp,
                    text = trail.description
                )
            }


        }
    }
}

private fun navigateToTrailDetails(navController: NavController, trail: Trail) {
    // Navigate to TrailDetailsScreen with the selected trail
    navController.navigate("trailDetails/${trail.id}")
}
