// this file contains the login screen composable
package com.d00223094.hostlockmobile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.d00223094.hostlockmobile.data.Home
import com.d00223094.hostlockmobile.ui.theme.HostLockMobileTheme

@Composable
fun LoginScreen(navController: NavController) {
    // main layout column
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // screen title placeholder
        Text(
            text = "Login Screen Placeholder (Username/Password Form)",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        // temporary button to skip the login process
        Button(
            onClick = {
                // navigate to home screen
                navController.navigate(Home.route) {
                    // removes the login screen from the back stack
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("TEMP: Bypass to Home")
        }
    }
}

// preview function for the screen
@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    HostLockMobileTheme {
        LoginScreen(navController = rememberNavController())
    }
}