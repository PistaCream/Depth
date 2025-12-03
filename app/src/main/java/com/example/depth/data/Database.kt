package com.example.depth.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.depth.data.dao.TimeLogDao
import com.example.depth.data.entity.TimeLog

object DatabaseProvider {
    fun provideDB(context: Context): AppDB {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDB::class.java,
            "notes.db"
        ).build()
    }
}

@Database(entities = [TimeLog::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun timeLogDao(): TimeLogDao
}