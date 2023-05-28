package com.base.presentation.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = koinViewModel()) {
    val publicationsState = homeViewModel.publicationState.collectAsState().value

    publicationsState.forEach {
        Text(text = it.title)
        Spacer(modifier = Modifier.size(20.dp))
    }
}