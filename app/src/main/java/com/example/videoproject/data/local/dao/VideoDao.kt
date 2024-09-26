package com.example.videoproject.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.videoproject.data.local.entities.VideoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllVideos(videoList: List<VideoEntity>)

    @Query("Select * From video_table")
    fun getAllVideos(): Flow<List<VideoEntity>>
}