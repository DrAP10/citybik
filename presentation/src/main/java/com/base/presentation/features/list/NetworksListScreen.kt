package com.base.presentation.features.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.base.domain.Answer
import com.base.domain.Network
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    goToDetails: (network: Network) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NetworksListViewModel = koinViewModel()
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {

        var text by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = text,
            onValueChange = { term ->
                text = term
                viewModel.getNetworksList(term)
            },
            label = {
                Text("Search")
            },
            placeholder = {
                Text("Filter by a term")
            },
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        when (val state = viewModel.networksState.collectAsState().value) {
            is Answer.Success -> NetworksList(state.data, goToDetails)
            is Answer.NetworkError -> Message("Connection error!")
            is Answer.Error -> Message("Error! \ncode: ${state.code}, message: ${state.message}")
            is Answer.UnknownError -> Message("Unknown error!")
            is Answer.Loading -> Loading()
        }
    }

}

@Composable
fun NetworksList(
    networks: List<Network>,
    goToDetails: (network: Network) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(networks) { network ->
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .clickable { goToDetails(network) }
            ) {
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = network.name)
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = network.company)
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = "${network.location.city}, ${network.location.country}")
                Spacer(modifier = Modifier.size(8.dp))
                network.location.coordinates.apply {
                    Text(text = "$latitude, $longitude")
                }
                Spacer(modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Composable
private fun Loading() {
    Message(text = "Loading...")
}

@Composable
private fun Message(text: String) {
    Text(text = text)
    Spacer(modifier = Modifier.size(20.dp))
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun Preview() {
    
}