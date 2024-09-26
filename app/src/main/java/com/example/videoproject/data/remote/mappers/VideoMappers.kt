package com.example.videoproject.data.remote.mappers

import com.example.videoproject.data.local.entities.VideoEntity
import com.example.videoproject.data.remote.dto.VideoDTO
import com.example.videoproject.domain.models.Video

fun VideoDTO.toVideoEntity() =
    VideoEntity(
        videoUrl = this.videoUrl,
        thumb = this.thumb,
        title = this.title,
        subtitle = this.subtitle,
    )

fun VideoEntity.toVideo() =
    Video(
        videoUrl = this.videoUrl,
        thumb = this.thumb,
        title = this.title,
        subtitle = this.subtitle,
    )