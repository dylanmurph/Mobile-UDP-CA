// this file contains the placeholder for the home screen
package com.d00223094.hostlockmobile.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d00223094.hostlockmobile.R
import com.d00223094.hostlockmobile.ui.MarsPhotoUiModel
import com.d00223094.hostlockmobile.ui.MarsUiState
import com.d00223094.hostlockmobile.ui.MarsViewModel
import com.d00223094.hostlockmobile.ui.theme.HostLockMobileTheme

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    val marsViewModel: MarsViewModel = viewModel()
    val marsUiState = marsViewModel.marsUiState

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        AsyncImage(
            model = "https://hostlocksd3b.online/logo192.png",
            contentDescription = "HostLock Logo",
            modifier = Modifier.size(120.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(contentAlignment = Alignment.Center) {
            when (marsUiState) {
                is MarsUiState.Loading -> LoadingScreen()
                is MarsUiState.Success -> MarsPhotoCard(
                    photo = marsUiState.photo,
                    retryAction = { marsViewModel.getMarsPhotos() },
                    modifier = Modifier.fillMaxWidth()
                )
                is MarsUiState.Error -> ErrorScreen(retryAction = { marsViewModel.getMarsPhotos() })
            }
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = ""
        )
        Text(text = stringResource(R.string.app_name), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text("Retry")
        }
    }
}

@Composable
fun MarsPhotoCard(photo: MarsPhotoUiModel, retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(photo.imgSrc.replace("http://", "https://"))
                    .crossfade(true)
                    .build(),
                error = painterResource(R.drawable.ic_launcher_background),
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "Mars Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Button(onClick = retryAction, modifier = Modifier.padding(8.dp)) {
                Text("Refresh Photo")
            }
        }
    }
}

// preview function for the screen
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HostLockMobileTheme {
        ErrorScreen(retryAction = { })
    }
}