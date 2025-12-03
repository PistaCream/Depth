package com.example.depth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.depth.data.DatabaseProvider
import com.example.depth.presentation.AppNavigation
import com.example.depth.presentation.history.HistoryViewModel
import com.example.depth.ui.theme.DepthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val dao = DatabaseProvider.provideDB(applicationContext).timeLogDao()
        val historyViewModel = HistoryViewModel(dao)

        setContent {
            DepthTheme {
                AppNavigation(historyViewModel = historyViewModel)
            }
        }
    }
}
