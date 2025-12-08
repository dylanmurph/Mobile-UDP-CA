// this file contains the placeholder for the home screen
package com.d00223094.hostlockmobile.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.d00223094.hostlockmobile.ui.theme.HostLockMobileTheme

@Composable
fun HomeScreen(navController: NavController) {
    // screen content container
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        // screen title placeholder
        Text(text = "Home Screen Placeholder (Single BnB / Device)", style = MaterialTheme.typography.titleLarge)
    }
}

// preview function for the screen
@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HostLockMobileTheme {
        HomeScreen(navController = rememberNavController())
    }
}