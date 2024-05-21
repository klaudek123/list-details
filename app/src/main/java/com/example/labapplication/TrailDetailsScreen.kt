package com.example.labapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign


@Composable
fun TrailDetailsScreen(trail: Trail) {
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.extras?.get("data")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(
                Color(0xFFD7D2E4),
            )
            .padding(16.dp),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = trail.title,
            fontWeight = FontWeight.Bold,
            fontSize = if (DeviceConfig.isTablet()) 62.sp else 40.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            fontSize = if (DeviceConfig.isTablet()) 42.sp else 24.sp,
            text = trail.description
        )
        Spacer(modifier = Modifier.height(16.dp))
        val image = painterResource(id = trail.idImage)
        Row(
            modifier = Modifier
                .padding(16.dp),
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .width(if (DeviceConfig.isTablet()) 450.dp else 300.dp)
                    .height(if (DeviceConfig.isTablet()) 300.dp else 200.dp) // Wysokość obrazu
                    .clip(RoundedCornerShape(if (DeviceConfig.isTablet()) 16.dp else 8.dp)) // Zaokrąglone narożniki obrazu
            )
            Spacer(modifier = Modifier.height(16.dp))
            FAB(launcher)
        }

        Spacer(modifier = Modifier.height(16.dp))
        CountdownTimer(trail.id)
    }

}



@Composable
fun CountdownTimer(id: Int) {
    val context = LocalContext.current
    val sharedPreferences = remember { context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE) }

    var elapsedTime by remember { mutableLongStateOf(sharedPreferences.getLong("elapsedTime$id", 0L)) }
    var isTimerRunning by remember { mutableStateOf(sharedPreferences.getBoolean("isTimerRunning$id", false)) }

    val timerHandler = remember { Handler(Looper.getMainLooper()) }

    DisposableEffect(Unit) {
        onDispose {
            timerHandler.removeCallbacksAndMessages(null)
            with(sharedPreferences.edit()) {
                putLong("elapsedTime$id", elapsedTime)
                putBoolean("isTimerRunning$id", isTimerRunning)
                apply()
            }
        }
    }

    if (isTimerRunning) {
        timerHandler.postDelayed({
            elapsedTime += 1000L
        }, 1000L)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatTime(elapsedTime),
            fontWeight = FontWeight.Bold,
            fontSize = if (DeviceConfig.isTablet()) 42.sp else 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    isTimerRunning = true
                }
            ) {
                Text(
                    text = "Start",
                    fontSize = if (DeviceConfig.isTablet()) 32.sp else 20.sp
                )
            }
            Button(
                onClick = {
                    isTimerRunning = false
                }
            ) {
                Text(
                    text = "Stop",
                    fontSize = if (DeviceConfig.isTablet()) 32.sp else 20.sp
                )
            }
            Button(
                onClick = {
                    isTimerRunning = false
                    elapsedTime = 0L
                }
            ) {
                Text(
                    text = "Przerwij",
                    fontSize = if (DeviceConfig.isTablet()) 32.sp else 20.sp
                )
            }
        }
    }
}

@SuppressLint("DefaultLocale")
private fun formatTime(timeInMillis: Long): String {
    val seconds = (timeInMillis / 1000).toInt()
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val remainingSeconds = seconds % 60
    return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)
}

@Composable
fun FAB(launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
    FloatingActionButton(
        onClick = {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            launcher.launch(cameraIntent)
        }
    ) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}