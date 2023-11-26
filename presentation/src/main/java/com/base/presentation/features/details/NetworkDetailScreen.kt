package com.base.presentation.features.details

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.base.domain.Answer
import com.base.domain.Coordinates
import com.base.domain.Network
import org.koin.androidx.compose.koinViewModel

@Composable
fun NetworkDetailScreen(
    networkId: String?,
    openMaps: (coordinates: Coordinates) -> Unit,
    modifier: Modifier = Modifier,
    networkDetailViewModel: NetworkDetailViewModel = koinViewModel()
) {
    LaunchedEffect(networkId) {
        networkId?.let { networkDetailViewModel.getNetworkDetail(it) }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        when (val state = networkDetailViewModel.networkState.collectAsState().value) {
            is Answer.Success -> SuccessScreen(state.data, openMaps)
            is Answer.NetworkError -> Message("Connection error!")
            is Answer.Error -> Message("Error! \ncode: ${state.code}, message: ${state.message}")
            is Answer.UnknownError -> Message("Unknown error!")
            is Answer.Loading -> Loading()
            is Answer.ErrorWithLocalData -> SuccessScreen(state.data, openMaps, withLocalData = true)
        }
    }

}

@Composable
private fun SuccessScreen(
    network: Network,
    openMaps: (coordinates: Coordinates) -> Unit,
    withLocalData: Boolean = false
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = withLocalData) {
        if (withLocalData) {
            Toast.makeText(context, "Data could be outdated", Toast.LENGTH_SHORT).show()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clickable { openMaps(network.location.coordinates) }
    ) {
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "${network.name} (${network.id})")
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = network.company)
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = "${network.location.city}, ${network.location.country}")
        Spacer(modifier = Modifier.size(8.dp))
        network.location.coordinates.apply {
            Text(text = "Location: ($latitude, $longitude)")
        }
        Spacer(modifier = Modifier.size(8.dp))
        Divider(color = MaterialTheme.colors.onBackground, thickness = 1.dp)
    }
    network.stations?.let { stations ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp)
        ) {
            items(stations) { station ->
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .clickable { openMaps(station.coordinates) }
                ) {
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(text = station.name)
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = "Last update: ${station.formattedLastUpdate}")
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = "Free bikes: ${station.freeBikes}, Empty slots: ${station.emptySlots}")
                    Spacer(modifier = Modifier.size(8.dp))
                    station.coordinates.apply {
                        Text(text = "Location: ($latitude, $longitude)")
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Divider(
                        color = MaterialTheme.colors.onBackground,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 60.dp)
                    )
                }
            }
        }
    }


}

@Composable
private fun Loading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
        )
    }
}

@Composable
private fun Message(text: String) {
    Text(text = text)
    Spacer(modifier = Modifier.size(20.dp))
}