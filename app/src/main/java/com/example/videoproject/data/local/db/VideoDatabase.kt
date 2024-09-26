package com.example.videoproject.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.videoproject.data.local.dao.VideoDao
import com.example.videoproject.data.local.entities.VideoEntity

@Database(entities = [VideoEntity::class], version = 1)
abstract class VideoDatabase: RoomDatabase() {
    abstract fun videoDao(): VideoDao
}