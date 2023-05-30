package com.base.presentation.features.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.base.domain.Answer
import com.base.domain.Publication
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    goToSettings: () -> Unit,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = koinViewModel()
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {

        when (val publicationsState = homeViewModel.publicationState.collectAsState().value) {
            is Answer.Success -> Publications(publicationsState)
            is Answer.NetworkError -> Message("Connection error!")
            is Answer.Error -> Message("Error! \ncode: ${publicationsState.code}, message: ${publicationsState.message}")
            is Answer.UnknownError -> Message("Unknown error!")
            is Answer.Loading -> LoadingPublications()
        }

        Spacer(modifier = Modifier.size(20.dp))
        Button(
            onClick = goToSettings,
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.Gray
            ),
        ) {
            Text(text = "Setting", color = Color.Black)
        }
    }

}

@Composable
private fun Publications(publicationsState: Answer.Success<Publication>) {
    Message(text = publicationsState.data.message)
}

@Composable
private fun LoadingPublications() {
    Message(text = "Loading...")
}

@Composable
private fun Message(text: String) {
    Text(text = text)
    Spacer(modifier = Modifier.size(20.dp))
}