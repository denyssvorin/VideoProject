package com.example.videoproject.data.remote.api

import com.example.videoproject.data.remote.dto.VideoDTO
import retrofit2.http.GET

interface VideoApi {

    @GET("endpoint")
    fun getVideoList(): List<VideoDTO>
}