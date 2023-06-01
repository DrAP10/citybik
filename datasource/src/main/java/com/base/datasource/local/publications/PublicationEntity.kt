package com.base.datasource.local.publications

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PUBLICATION")
data class PublicationEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "sub_title") val subTitle: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "body") val body: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "likes") val likes: Int,
)