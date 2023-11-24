package com.base.datasource.remote.networks.model


data class NetworkDto(
    val id: String,
    val name: String,
//    val company: String, -- Not parsing company due an error in the api https://github.com/eskerda/pybikes/issues/644
    val location: LocationDto,
    val stations: List<StationDto>?
)