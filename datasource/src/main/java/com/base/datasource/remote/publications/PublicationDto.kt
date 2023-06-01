package com.base.datasource.remote.publications

data class PublicationDto(
    val id: Long,
    val title: String,
    val subtitle: String,
    val author: String,
    val body: String,
    val imageUrl: String,
    val date: String,
    val likes: Int,
)