package com.base.datasource.local.networks

import com.base.data.sources.local.NetworkLocalDataSource
import com.base.domain.Coordinates
import com.base.domain.Location
import com.base.domain.Network
import com.base.domain.Station
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class NetworkLocalDataSourceImpl(
    private val networksDao: NetworksDao
) : NetworkLocalDataSource {
    override suspend fun insertOrUpdateNetworks(networks: List<Network>) {
        networksDao.insertNetworksWithStations(*networks.map { it.toEntity() }.toTypedArray())
    }

    override suspend fun insertOrUpdateNetwork(network: Network) {
        networksDao.insertNetworksWithStations(network.toEntity())
    }

    override suspend fun getNetworks(term: String): Flow<List<Network>> =
        networksDao.getNetworks(term).map { list -> list.map { item -> item.toBo() } }

    override suspend fun getNetworkDetail(networkId: String): Flow<Network?> =
        networksDao.getNetworkDetail(networkId).map { it?.toBo() }

    override suspend fun deleteAll() {
        networksDao.deleteAllStations()
        networksDao.deleteAllNetworks()
    }

}

private fun Network.toEntity() = NetworksWithStations(
    network = NetworkEntity(
        id = id,
        name = name,
        company = company,
        location = location.toEntity(),
    ),
    stations = stations?.map { it.toEntity(networkId = id) } ?: emptyList(),
)

private fun NetworksWithStations.toBo() = Network(
    id = network.id,
    name = network.name,
    company = network.company,
    location = network.location.toBo(),
    stations = stations.map { it.toBo() },
)

private fun Location.toEntity() = LocationEntity(
    city = city,
    latitude = coordinates.latitude,
    longitude = coordinates.longitude,
    country = country,
)

private fun LocationEntity.toBo() = Location(
    city = city,
    coordinates = Coordinates(latitude, longitude),
    country = country,
)

private fun Station.toEntity(networkId: String) = StationEntity(
    id = id,
    networkId = networkId,
    name = name,
    timestamp = timestamp?.time,
    freeBikes = freeBikes,
    emptySlots = emptySlots,
    latitude = coordinates.latitude,
    longitude = coordinates.longitude,
)

private fun StationEntity.toBo() = Station(
    id = id,
    name = name,
    timestamp = timestamp?.let { Date(it) },
    freeBikes = freeBikes,
    emptySlots = emptySlots,
    coordinates = Coordinates(latitude, longitude),
)