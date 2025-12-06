package com.example.depth.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.depth.data.dao.TimeLogDao
import kotlinx.coroutines.launch

class HistoryViewModel(private val timeLogDao: TimeLogDao) : ViewModel() {
    private var _allLogs = mutableListOf<TimeLogDao.DateTotal>()
    val allLogs: List<TimeLogDao.DateTotal> = _allLogs

    fun getTotalMinutesAllDates() {
        viewModelScope.launch {
            val logs = timeLogDao.getTotalMinutesAllDates()
            _allLogs = logs.toMutableList()
        }
    }
}

