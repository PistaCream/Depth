package com.example.depth.presentation.timer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.Timer
import kotlin.concurrent.fixedRateTimer

//TODO: duration should be configurable
const val TIMER_DURATION_SECONDS = 10

@Composable
fun TimerScreen(timerViewModel: TimerViewModel) {
    var timer by remember { mutableStateOf<Timer?>(null) }
    var startTimer by remember { mutableStateOf(false) }
    var timerProgressSeconds by remember { mutableIntStateOf(0) }

    fun timerFinished() {
        timerViewModel.logTime(TIMER_DURATION_SECONDS)
    }

    LaunchedEffect(startTimer) {
        timer?.cancel()
        if (startTimer) {
            timer = fixedRateTimer(period = 1000L) {
                timerProgressSeconds++
                if (timerProgressSeconds >= TIMER_DURATION_SECONDS) {
                    cancel()
                    timerFinished()
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
            Text(text = getRemainingTime(TIMER_DURATION_SECONDS - timerProgressSeconds))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Spacer(Modifier.height(100.dp))
                IconButton(onClick = {
                    timer?.cancel()
                    startTimer = false
                    timerProgressSeconds = 0
                }) {
                    Icon(Icons.Default.Refresh, "Reset timer")
                }
            }
        }
        if (startTimer && timerProgressSeconds < TIMER_DURATION_SECONDS) {
            IconButton(onClick = {
                startTimer = false
            }) {
                Icon(Icons.Default.Pause, "Pause timer")
            }
        } else if (timerProgressSeconds < TIMER_DURATION_SECONDS) {
            IconButton(onClick = {
                startTimer = true
            }) {
                Icon(Icons.Default.PlayArrow, "Start timer")
            }
        }
    }
}

@Composable
fun DeterminateCircularProgress(progress: Int, modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        progress = {
            progress.toFloat() / TIMER_DURATION_SECONDS.toFloat()
        },
        modifier = modifier,
        color = Color.Blue,
        trackColor = Color.LightGray,
    )
}