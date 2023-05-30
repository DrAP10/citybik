package com.base.presentation.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.base.domain.Answer
import com.base.domain.Publication
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = koinViewModel()) {

    when (val publicationsState = homeViewModel.publicationState.collectAsState().value) {
        is Answer.Success -> Publications(publicationsState)
        is Answer.NetworkError -> Message("Connection error!")
        is Answer.Error -> Message("Error! \ncode: ${publicationsState.code}, message: ${publicationsState.message}")
        is Answer.UnknownError -> Message("Unknown error!")
        is Answer.Loading -> LoadingPublications()
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