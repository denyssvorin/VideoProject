package com.example.videoproject.data.repo

import com.example.videoproject.data.local.dao.VideoDao
import com.example.videoproject.data.remote.api.VideoApi
import com.example.videoproject.data.remote.dto.VideoDTO
import com.example.videoproject.data.remote.mappers.toVideo
import com.example.videoproject.data.remote.mappers.toVideoEntity
import com.example.videoproject.di.BackgroundCoroutineScope
import com.example.videoproject.domain.models.Video
import com.example.videoproject.domain.repo.VideoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val videoApi: VideoApi,
    private val videoDao: VideoDao,
    @BackgroundCoroutineScope private val coroutineScope: CoroutineScope
) : VideoRepository {

    init {
        getVideosFromApi()
    }

    private fun getVideosFromApi() = coroutineScope.launch {
        try {
            val dataFromApi = getVideoList().map { it.toVideoEntity() }
            videoDao.insertAllVideos(dataFromApi)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getVideosFromDB(): Flow<List<Video>> {
        return videoDao.getAllVideos().map { videoEntities ->
            videoEntities.map { it.toVideo() }
        }
    }


    private fun getVideoList(): List<VideoDTO> {
        return listOf(
            VideoDTO(
                videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                subtitle = "By Blender Foundation",
                thumb = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/BigBuckBunny.jpg",
                title = "Big Buck Bunny"
            ),
            VideoDTO(
                videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
                subtitle = "By Blender Foundation",
                thumb = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ElephantsDream.jpg",
                title = "Elephant Dream"
            ),
            VideoDTO(
                videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
                subtitle = "By Google",
                thumb = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerBlazes.jpg",
                title = "For Bigger Blazes"
            ),
            VideoDTO(
                videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
                subtitle = "By Google",
                thumb = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerEscapes.jpg",
                title = "For Bigger Escape"
            ),
            VideoDTO(
                videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
                subtitle = "By Google",
                thumb = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerFun.jpg",
                title = "For Bigger Fun"
            ),
            VideoDTO(
                videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
                subtitle = "By Google",
                thumb = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerJoyrides.jpg",
                title = "For Bigger Joyrides"
            ),
            VideoDTO(
                videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
                subtitle = "By Google",
                thumb = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerMeltdowns.jpg",
                title = "For Bigger Meltdowns"
            ),
            VideoDTO(
                videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4",
                subtitle = "By Blender Foundation",
                thumb = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/Sintel.jpg",
                title = "Sintel"
            ),
            VideoDTO(
                videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4",
                subtitle = "By Garage419",
                thumb = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/SubaruOutbackOnStreetAndDirt.jpg",
                title = "Subaru Outback On Street And Dirt"
            ),
            VideoDTO(
                videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4",
                subtitle = "By Blender Foundation",
                thumb = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/TearsOfSteel.jpg",
                title = "Tears of Steel"
            ),
            VideoDTO(
                videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4",
                subtitle = "By Garage419",
                thumb = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/VolkswagenGTIReview.jpg",
                title = "Volkswagen GTI Review"
            ),
            VideoDTO(
                videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",
                subtitle = "By Garage419",
                thumb = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/WeAreGoingOnBullrun.jpg",
                title = "We Are Going On Bullrun"
            ),
            VideoDTO(
                videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4",
                subtitle = "By Garage419",
                thumb = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/WhatCarCanYouGetForAGrand.jpg",
                title = "What care can you get for a grand?"
            )
        )
    }
}