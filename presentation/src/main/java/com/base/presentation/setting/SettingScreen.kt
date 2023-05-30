package com.base.presentation.setting

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingScreen(
    onBack: () -> Unit,
    settingViewModel: SettingViewModel = koinViewModel()
) {

    Button(
        onClick = onBack, colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color.Gray
        )
    ) {
        Text(text = "Go back", color = Color.Black)
    }

}