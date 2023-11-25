package com.base.datasource.remote.networks.model


data class NetworkDto(
    val id: String,
    val name: String,
    val company: List<String>,
    val location: LocationDto,
    val stations: List<StationDto>?
)