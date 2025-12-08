// this is the main entry point for the android application
package com.d00223094.hostlockmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.d00223094.hostlockmobile.ui.navigation.AppNavigation
import com.d00223094.hostlockmobile.ui.theme.HostLockMobileTheme

class MainActivity : ComponentActivity() {
    // main entry method when the app starts
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set the content to the jetpack compose ui
        setContent {
            HostLockMobileTheme {
                // a simple container that uses the app's background color
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // start the application's navigation
                    AppNavigation()
                }
            }
        }
    }
}