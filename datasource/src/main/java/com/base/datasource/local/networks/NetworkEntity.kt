package com.base.datasource.local.networks

import androidx.room.*

@Entity(tableName = "NETWORK")
data class NetworkEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "company") val company: String,
    @Embedded val location: LocationEntity,
)

data class LocationEntity(
    val city: String,
    val latitude: Float,
    val longitude: Float,
    val country: String,
)

@Entity(tableName = "STATION")
data class StationEntity(
    @PrimaryKey val id: String,
    val networkId: String,
    val name: String,
    val timestamp: Long?,
    val freeBikes: Int,
    val emptySlots: Int,
    val latitude: Float,
    val longitude: Float,
)

data class NetworksWithStations(
    @Embedded val network: NetworkEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "networkId"
    )
    val stations: List<StationEntity>
)