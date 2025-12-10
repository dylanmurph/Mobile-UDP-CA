package com.d00223094.hostlockmobile.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.d00223094.hostlockmobile.data.DeviceViewModel
import com.d00223094.hostlockmobile.data.MockData
import com.d00223094.hostlockmobile.ui.theme.HostLockMobileTheme


@Composable
fun AccessLogsScreen(
    navController: NavController,
    viewModel: DeviceViewModel
) {
    val accessLogs by viewModel.accessLogs.collectAsState()
    var expandedIndex by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val randomLog = MockData.mockAccessLogs.randomOrNull()
                if (randomLog != null) {
                    viewModel.addAccessLog(
                        property = randomLog.property,
                        fobNumber = randomLog.fobNumber,
                        status = randomLog.status,
                        date = randomLog.date
                    )
                }
            },
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Random Log")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
        ) {
            Text(
                text = "Access Logs",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (accessLogs.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.History,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .height(64.dp)
                            .fillMaxWidth(),
                        tint = MaterialTheme.colorScheme.surfaceVariant
                    )
                    Text(
                        text = "No logs found",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    itemsIndexed(accessLogs) { index, log ->
                        val isExpanded = expandedIndex == index
                        ElevatedCard(
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                            colors = CardDefaults.elevatedCardColors(
                                containerColor = if (isExpanded) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.surface,
                                contentColor = if (isExpanded) MaterialTheme.colorScheme.onTertiaryContainer else MaterialTheme.colorScheme.onSurface
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    expandedIndex = if (isExpanded) null else index
                                }
                                .animateContentSize(
                                    animationSpec = spring(
                                        dampingRatio = Spring.DampingRatioMediumBouncy,
                                        stiffness = Spring.StiffnessLow
                                    )
                                )
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.History,
                                        contentDescription = null,
                                        tint = if (isExpanded) MaterialTheme.colorScheme.onTertiaryContainer else MaterialTheme.colorScheme.tertiary
                                    )
                                    Spacer(modifier = Modifier.padding(8.dp))
                                    Text(
                                        text = "${log.status} on ${log.property}",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Icon(
                                        imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                        contentDescription = if (isExpanded) "Collapse" else "Expand"
                                    )
                                }
                                if (isExpanded) {
                                    Column(
                                        modifier = Modifier.padding(top = 8.dp),
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        Text(text = "Date: ${log.date}")
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(text = "Fob Number: ${log.fobNumber}")


                                        Button(
                                            onClick = {
                                                viewModel.deleteAccessLog(log.id)
                                                expandedIndex = null
                                            },
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = MaterialTheme.colorScheme.error,
                                                contentColor = MaterialTheme.colorScheme.onError
                                            ),
                                            modifier = Modifier.align(Alignment.End)
                                        ) {
                                            Text("Delete Entry")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AccessLogsScreenPreview() {
    HostLockMobileTheme {
        Text("Preview disabled due to ViewModel")
    }
}