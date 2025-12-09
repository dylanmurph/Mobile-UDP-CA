// this file contains the placeholder for the access logs screen
package com.d00223094.hostlockmobile.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
    val accessLogs = (1..20).map { "Access Log #$it" } // Sample data
    var expandedIndex by remember { mutableStateOf<Int?>(null) }

    // screen content container
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        // screen title
        Text(text = "Access Logs  Screen Placeholder (List/Filter logs)", style = MaterialTheme.typography.titleLarge)

        // scrollable list of access logs
        LazyColumn(
            modifier = Modifier.padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(accessLogs) { index, log ->
                val isExpanded = expandedIndex == index
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            expandedIndex = if (isExpanded) null else index
                        }
                        .animateContentSize()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = log,
                                modifier = Modifier.weight(1f)
                            )
                            Icon(
                                imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                contentDescription = if (isExpanded) "Collapse" else "Expand"
                            )
                        }
                        if (isExpanded) {
                            Text(
                                text = "more info for $log blah blah blah",
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }
            }
        }
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