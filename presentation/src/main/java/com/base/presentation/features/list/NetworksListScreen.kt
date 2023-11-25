package com.base.presentation.features.list

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.base.domain.Answer
import com.base.domain.Network
import org.koin.androidx.compose.koinViewModel

@Composable
fun NetworksListScreen(
    goToDetails: (network: Network) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NetworksListViewModel = koinViewModel()
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {

        var term by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = term,
            onValueChange = { newTerm ->
                term = newTerm
                viewModel.getNetworksList(newTerm)
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
            is Answer.Success -> NetworksList(state.data, term, goToDetails)
            is Answer.NetworkError -> Message("Connection error!")
            is Answer.Error -> Message("Error! \ncode: ${state.code}, message: ${state.message}")
            is Answer.UnknownError -> Message("Unknown error!")
            is Answer.Loading -> Loading()
            is Answer.ErrorWithLocalData -> NetworksList(state.data, term, goToDetails, withLocalData = true)
        }
    }

}

@Composable
fun NetworksList(
    networks: List<Network>,
    term: String,
    goToDetails: (network: Network) -> Unit,
    withLocalData: Boolean = false
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = withLocalData) {
        if (withLocalData) {
            Toast.makeText(context, "Data could be outdated", Toast.LENGTH_SHORT).show()
        }
    }

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(networks) { network ->
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .clickable { goToDetails(network) }
            ) {
                Spacer(modifier = Modifier.size(10.dp))
                if (network.name.contains(term, ignoreCase = true)) {
                    val start = network.name.indexOf(term, ignoreCase = true)
                    val spanStyles = listOf(
                        AnnotatedString.Range(
                            SpanStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                            start = start,
                            end = start + term.length
                        )
                    )
                    Text(
                        text = AnnotatedString(text = network.name, spanStyles = spanStyles),
                        fontSize = 18.sp
                    )
                } else {
                    Text(text = network.name, fontSize = 18.sp)
                }
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

@Preview(showBackground = true, widthDp = 360)
@Composable
fun Preview() {

}