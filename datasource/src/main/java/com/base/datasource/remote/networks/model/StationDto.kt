package com.base.datasource.remote.networks.model

import com.google.gson.annotations.SerializedName

data class StationDto(
    val id: String,
    val name: String,
    val timestamp: String,
    @SerializedName("free_bikes") val freeBikes: Int,
    @SerializedName("empty_slots") val emptySlots: Int,
    val latitude: Float,
    val longitude: Float,
)