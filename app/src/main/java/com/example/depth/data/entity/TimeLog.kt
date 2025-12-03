package com.example.depth.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TimeLog(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,
    val minutes: Int,
)