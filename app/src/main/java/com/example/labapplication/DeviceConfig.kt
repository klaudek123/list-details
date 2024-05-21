package com.example.labapplication

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

object DeviceConfig {
    @Composable
    fun isTablet(): Boolean {
        val screenWidthDp = LocalConfiguration.current.screenWidthDp

        return screenWidthDp >= 600
    }
}