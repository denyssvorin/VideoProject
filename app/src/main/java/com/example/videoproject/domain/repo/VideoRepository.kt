package com.example.videoproject.domain.repo

import com.example.videoproject.domain.models.Video
import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    fun getVideosFromDB(): Flow<List<Video>>
}