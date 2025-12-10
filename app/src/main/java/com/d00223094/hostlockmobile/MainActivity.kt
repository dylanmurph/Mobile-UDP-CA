// this is the main entry point for the android application
package com.d00223094.hostlockmobile


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.d00223094.hostlockmobile.data.DeviceViewModel
import com.d00223094.hostlockmobile.data.DeviceViewModelFactory
import com.d00223094.hostlockmobile.ui.navigation.AppNavigation
import com.d00223094.hostlockmobile.ui.theme.HostLockMobileTheme

class MainActivity : ComponentActivity() {
    // main entry method when the app starts
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        // set the content to the jetpack compose ui
        setContent {
            HostLockMobileTheme {
                // a simple container that uses the app's background color
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Get the application instance to access the repository
                    val application = LocalContext.current.applicationContext as HostLockApplication

                    // Create the ViewModel using the factory and repository
                    val viewModel: DeviceViewModel = viewModel(
                        factory = DeviceViewModelFactory(application.repository)
                    )
                    // start the application's navigation
                    AppNavigation(viewModel = viewModel)
                }
            }
        }
    }
}