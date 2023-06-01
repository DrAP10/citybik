package com.base.presentation.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.base.domain.Answer
import com.base.domain.Publication
import com.base.presentation.R
import org.koin.androidx.compose.koinViewModel
import java.util.*

@Composable
fun HomeScreen(
    goToSettings: () -> Unit,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = koinViewModel()
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {

        when (val publicationsState = homeViewModel.publicationState.collectAsState().value) {
            is Answer.Success -> Publications(publicationsState.data)
            is Answer.NetworkError -> Message("Connection error!")
            is Answer.Error -> Message("Error! \ncode: ${publicationsState.code}, message: ${publicationsState.message}")
            is Answer.UnknownError -> Message("Unknown error!")
            is Answer.Loading -> LoadingPublications()
        }

        Divider(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray))
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
fun Publications(publication: Publication) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4f / 3f)
        ) {
            AsyncImage(
                model = publication.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(10.dp)
                    .clip(CircleShape)
                    .background(Color(0f, 0f, 0f, 0.33f))
                    .padding(4.dp)
            ) {
                Text(
                    text = publication.likes.toString(),
                    color = Color.White,
                    modifier = Modifier.padding(end = 5.dp)
                )
                Image(painter = painterResource(id = R.drawable.ic_like), contentDescription = null)
            }
        }
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = publication.title)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = publication.subTitle)
            Spacer(modifier = Modifier.size(20.dp))
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

@Preview(showBackground = true, widthDp = 360)
@Composable
fun Preview() {
    val publication = Publication(
        2,
        "Titulo2",
        "Subtitulo",
        "author",
        "body",
        "url",
        Date(),
        7
    )
    Publications(publication)
}