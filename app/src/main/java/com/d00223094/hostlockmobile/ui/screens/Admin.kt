// this file contains the placeholder for the admin maintenance screen
package com.d00223094.hostlockmobile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.d00223094.hostlockmobile.data.DeviceViewModel
import com.d00223094.hostlockmobile.ui.theme.HostLockMobileTheme

@Composable
fun AdminMaintenanceScreen(
    navController: NavController, viewModel: DeviceViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Admin Maintenance",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Button(
            onClick = { viewModel.clearAllAccessLogs() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Clear All Access Logs")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.clearAllGuests() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Clear All Guests")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { viewModel.clearAllData() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError
            )
        ) {
            Text("Clear ALL Database Entries")
        }
    }
}

// preview function for the screen
@Preview(showBackground = true)
@Composable
private fun AdminMaintenanceScreenPreview() {
    HostLockMobileTheme {
        //AdminMaintenanceScreen(navController = rememberNavController())
    }
}
