package com.example.labapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            val isDarkTheme = remember { mutableStateOf(false) }

            AppTheme(darkTheme = isDarkTheme.value) {
                TopBar(navController = navController, isDarkTheme = isDarkTheme) {
                    Navig(navController = navController)
                }
            }
        }
    }


}

@Composable
fun Main(navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Aplikacja szlaków",
                    fontWeight = FontWeight.Bold,
                    fontSize = if (DeviceConfig.isTablet()) 62.sp else 44.sp
                )
                Spacer(modifier = Modifier.height(if (DeviceConfig.isTablet()) 42.dp else 16.dp))
                Image(
                    painter = painterResource(id = R.drawable.szlak),
                    contentDescription = null,
                    modifier = Modifier
                        .width(if (DeviceConfig.isTablet()) 450.dp else 300.dp)
                        .height(if (DeviceConfig.isTablet()) 300.dp else 200.dp) // Wysokość obrazu
                        .clip(RoundedCornerShape(if (DeviceConfig.isTablet()) 16.dp else 8.dp)) // Zaokrąglone narożniki obrazu
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(if (DeviceConfig.isTablet()) 42.dp else 16.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { navController.navigate("category")},
                    modifier = Modifier.widthIn(max = 450.dp)
                ) {
                    Text(
                        text = "Przejdź do kategorii",
                        fontSize = if (DeviceConfig.isTablet()) 40.sp else 24.sp
                    )
                }
                Spacer(modifier = Modifier.height(if (DeviceConfig.isTablet()) 42.dp else 16.dp))
                Text(
                    text = "Autor: Klaudiusz Szklarkowski",
                    fontWeight = FontWeight.Bold,
                    fontSize = if (DeviceConfig.isTablet()) 32.sp else 16.sp
                )
                Spacer(modifier = Modifier.height(if (DeviceConfig.isTablet()) 42.dp else 16.dp))
            }
        }
    }
}



