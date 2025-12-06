package com.example.depth.presentation.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HistoryScreen(viewModel: HistoryViewModel) {
    viewModel.getTotalMinutesAllDates()
    val logs = viewModel.allLogs

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text("Testing")
        for (log in logs) {
            Text(text = "Date: ${log.date}, Total Minutes: ${log.total ?: 0}")
        }
    }
}
