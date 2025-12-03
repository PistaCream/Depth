package com.example.depth.presentation.history

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.depth.data.dao.TimeLogDao
import com.example.depth.data.entity.TimeLog
import kotlinx.coroutines.launch

class HistoryViewModel(private val timeLogDao: TimeLogDao) : ViewModel() {

    fun printAllLogs() {
        Log.d("HistoryViewModel", "Fetching all logs")
        viewModelScope.launch {
            val logs: List<TimeLog> = timeLogDao.getAllLogs()
            logs.forEach { log ->
                Log.d("HistoryViewModel", log.toString())
            }
        }
    }
}

