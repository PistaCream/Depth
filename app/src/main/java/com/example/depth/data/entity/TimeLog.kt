package com.example.depth.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class TimeLog(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: Date,
    val minutes: Int,
)