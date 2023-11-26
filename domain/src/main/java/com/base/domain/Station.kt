package com.base.domain

import java.text.SimpleDateFormat
import java.util.*

data class Station(
    val id: String,
    val name: String,
    val timestamp: Date?,
    val freeBikes: Int,
    val emptySlots: Int,
    val coordinates: Coordinates,
) {
    val formattedLastUpdate: String?
        get() = timestamp?.let {
            SimpleDateFormat("HH:mm:ss - dd MMM yyyy", Locale.getDefault()).format(timestamp)
        }
}