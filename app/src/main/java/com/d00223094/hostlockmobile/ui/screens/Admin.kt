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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


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

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Create New User",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))



                Button(
                    onClick = {
                        // Call the ViewModel function to add the user
                        if (name.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
                            viewModel.addUser(name,  password, email)
                            name = ""
                            email = ""
                            password = ""
                        }
                    },
                    modifier = Modifier.align(Alignment.End),
                    // Disable button if fields are empty
                    enabled = name.isNotBlank() && email.isNotBlank()
                ) {
                    Text("Create User")
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Database Operations",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
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

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewModel.clearAllUsers() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Clear All Users")
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
