package com.base.presentation.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = koinViewModel()) {
    val greetingState = homeViewModel.greetingState.collectAsState()
    Text(text = greetingState.value.value)
}