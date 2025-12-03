package com.example.depth.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.depth.data.entity.TimeLog

@Dao
interface TimeLogDao {
    @Upsert
    suspend fun upsert(timeLog: TimeLog)

    @Query("SELECT * FROM TimeLog ORDER BY id DESC")
    suspend fun getAllLogs(): List<TimeLog>
}