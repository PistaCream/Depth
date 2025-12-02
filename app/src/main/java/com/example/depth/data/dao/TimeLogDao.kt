package com.example.depth.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.depth.data.entity.TimeLog

@Dao
interface TimeLogDao {
    @Insert
    suspend fun insert(timeLog: TimeLog)

    @Query("SELECT * FROM TimeLog ORDER BY id DESC")
    suspend fun getAllLogs(): List<TimeLog>
}