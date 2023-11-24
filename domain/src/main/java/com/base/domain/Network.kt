package com.base.domain

data class Network(
    val id: String,
    val name: String,
    val company: String,
    val location: Location,
    val stations: List<Station>? = null
)