package com.example.talktoworld.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.talktoworld.R
import com.example.talktoworld.model.MarsPhoto

@Composable
fun HomeScreen(onReload:()->Unit , talkUiState: TalkUiState, modifier: Modifier, contentPaddingValues: PaddingValues = PaddingValues(
    0.dp
)
){
    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        when (talkUiState) {
            is TalkUiState.Success -> SuccessScreen(talkUiState.photos, modifier.fillMaxSize())
            is TalkUiState.Error -> ErrorScreen(onReload,modifier)
            is TalkUiState.Loading -> LoadingScreen(modifier)
        }
    }
}

@Composable
fun MarsPhotoCard(photos: List<MarsPhoto>, modifier: Modifier = Modifier){
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 150.dp)
    ){
         items(photos){
                ImageComposable(photo = it)
         }
    }

}

@Composable
fun ImageComposable(photo:MarsPhoto){
    Box {
        AsyncImage(
            error = painterResource(R.drawable.baseline_broken_image_24),
            placeholder = painterResource(R.drawable.loading_img),
            model = ImageRequest.Builder(context = LocalContext.current).data(photo.imgSrc).build(),
            contentDescription = stringResource(R.string.app_name),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .padding(5.dp)
                .clip(CircleShape)
        )
    }
}
@Composable
fun ErrorScreen(retryAction:()->Unit, modifier: Modifier){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.size(100.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_cloud_off_24),
                " ",
                modifier = modifier.size(100.dp)
            )

        }
        Text("Failed to Load")

        Button(onClick = retryAction,  ){
            Text(" Reload ")
        }
    }
}

@Composable
fun SuccessScreen( photos: List<MarsPhoto>, modifier: Modifier){

        MarsPhotoCard(photos = photos, modifier = modifier)

}

@Preview
@Composable
fun LoadingScreen(modifier: Modifier = Modifier){
    CircularProgressIndicator()
}