// this file contains the placeholder for the access logs screen
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.d00223094.hostlockmobile.data.DeviceViewModel
import com.d00223094.hostlockmobile.ui.theme.HostLockMobileTheme

@Composable
fun AccessLogsScreen(
    navController: NavController,
    // viewmodel can be injected here for data access
    viewModel: DeviceViewModel = viewModel()
) {
    // screen content container
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        // screen title placeholder
        Text(text = "Access Logs Screen Placeholder (List/Filter Logs)", style = MaterialTheme.typography.titleLarge)
    }
}

// preview function for the screen
@Preview(showBackground = true)
@Composable
private fun AccessLogsScreenPreview() {
    HostLockMobileTheme {
        AccessLogsScreen(navController = rememberNavController())
    }
}