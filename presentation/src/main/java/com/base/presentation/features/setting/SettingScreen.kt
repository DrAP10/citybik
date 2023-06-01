package com.base.presentation.features.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.base.domain.Answer
import com.base.domain.Publication
import com.base.presentation.features.home.Publications
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    settingViewModel: SettingViewModel = koinViewModel()
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        when (val publicationsState = settingViewModel.publicationState.collectAsState().value) {
            is Answer.Success -> SuccessScreen(
                publicationsState.data,
                onBack
            ) { settingViewModel.resetPublications() }
            is Answer.NetworkError -> Message("Connection error!")
            is Answer.Error -> Message("Error! \ncode: ${publicationsState.code}, message: ${publicationsState.message}")
            is Answer.UnknownError -> Message("Unknown error!")
            is Answer.Loading -> LoadingPublications()
        }
    }

}

@Composable
private fun SuccessScreen(
    publications: List<Publication>,
    onBack: () -> Unit,
    onPublicationReset: () -> Unit,
) {

    Column {

        Row {
            Button(
                onClick = onBack, colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Color.Gray
                )
            ) {
                Text(text = "Go back", color = Color.Black)
            }
            Button(
                onClick = onPublicationReset, colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Color.Gray
                )
            ) {
                Text(text = "Reset", color = Color.Black)
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(publications) { publication ->
                Publications(publication)
            }
        }
    }
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