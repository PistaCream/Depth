package com.example.depth.presentation.timer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.concurrent.fixedRateTimer

//TODO: duration should be configurable
const val TIMER_DURATION = 10

@Composable
fun TimerScreen() {
    var startTimer by remember { mutableStateOf(false) }
    var timerProgressSeconds by remember { mutableStateOf(0) }

    LaunchedEffect(startTimer) {
        if (startTimer) {
            fixedRateTimer(period = 1000L) {
                timerProgressSeconds++
                if (timerProgressSeconds >= TIMER_DURATION) {
                    cancel()
                }
            }
        }
    }

    fun getRemainingTime(remainingSeconds: Int): String {
        val minutes = remainingSeconds / 60
        val seconds = remainingSeconds % 60
        return "%02d:%02d".format(minutes, seconds)
    }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(contentAlignment = Alignment.Center) {
            DeterminateCircularProgress(progress = timerProgressSeconds, modifier = Modifier.size(270.dp))
            Text(text = getRemainingTime(TIMER_DURATION - timerProgressSeconds))
        }
        Button(onClick = { startTimer = true }) {
            Text("Start")
        }
    }
}

@Composable
fun DeterminateCircularProgress(progress: Int, modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        progress = {
            progress.toFloat() / TIMER_DURATION.toFloat()
        },
        modifier = modifier,
        color = Color.Blue,
        trackColor = Color.LightGray,
    )
}