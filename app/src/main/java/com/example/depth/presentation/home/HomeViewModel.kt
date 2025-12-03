package com.example.depth.presentation.home

import android.text.format.DateFormat
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.depth.data.dao.TimeLogDao
import kotlinx.coroutines.launch
import java.util.Date

class HomeViewModel(private val timeLogDao: TimeLogDao) : ViewModel() {
    private val _totalMinutes = mutableStateOf(0)
    val totalMinutes: State<Int> = _totalMinutes

    fun getTotalMinutesToday() {
        val dateString = DateFormat.format("yyyy-MM-dd", Date()).toString()
        viewModelScope.launch {
            val total = timeLogDao.getTotalMinutes(dateString) ?: 0
            _totalMinutes.value = total
        }
    }
}
