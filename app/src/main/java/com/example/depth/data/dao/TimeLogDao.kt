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

    @Query("SELECT SUM(minutes) FROM TimeLog WHERE date = :givenDate")
    suspend fun getTotalMinutes(givenDate: String): Int?

    //probably move to separate file
    data class DateTotal(
        val date: String,
        val total: Int?
    )

    //pagination?
    @Query("SELECT date, SUM(minutes) as total FROM TimeLog GROUP BY date ORDER BY date DESC")
    suspend fun getTotalMinutesAllDates(): List<DateTotal>
}