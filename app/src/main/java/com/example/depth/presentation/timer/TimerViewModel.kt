package com.example.depth.presentation.timer

import android.text.format.DateFormat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.depth.data.dao.TimeLogDao
import com.example.depth.data.entity.TimeLog
import kotlinx.coroutines.launch
import java.util.Date

class TimerViewModel(private val timeLogDao: TimeLogDao) : ViewModel() {

    fun logTime(durationSeconds: Int) {
        val dateString = DateFormat.format("yyyy-MM-dd", Date()).toString()
        viewModelScope.launch {
            val timeLog = TimeLog(
                date = dateString,
                minutes = durationSeconds / 60
            )
            timeLogDao.insert(timeLog)
        }
    }
}

