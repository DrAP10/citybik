package com.base.data.model.local

data class PublicationPojo(
    val id: Long,
    val title: String,
    val subTitle: String,
    val author: String,
    val body: String,
    val imageUrl: String,
    val date: Long,
    val likes: Int,
)