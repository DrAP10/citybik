package com.base.domain

import java.util.Date

data class Station(
    val id: String,
    val name: String,
    val timestamp: Date?,
    val freeBikes: Int,
    val emptySlots: Int,
    val coordinates: Coordinates,
)