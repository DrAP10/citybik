package com.base.datasource.remote.networks

import com.base.data.sources.remote.NetworkRemoteDataSource
import com.base.datasource.remote.base.wrapApiCall
import com.base.datasource.remote.networks.model.LocationDto
import com.base.datasource.remote.networks.model.NetworkDto
import com.base.datasource.remote.networks.model.StationDto
import com.base.domain.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.text.SimpleDateFormat
import java.util.*

class NetworkRemoteDataSourceImpl(
    private val networkApi: NetworksApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : NetworkRemoteDataSource {


    override suspend fun getNetworks(): Answer<List<Network>> = wrapApiCall(dispatcher) {
        networkApi.getNetworks().networks.map { it.toBo() }
    }

    override suspend fun getNetwork(networkId: String): Answer<Network>  = wrapApiCall(dispatcher) {
        networkApi.getNetwork(networkId).network.toBo()
    }

}

fun NetworkDto.toBo() = Network(
    id = id,
    name = name,
    company = company.first(),
    location = location.toBo(),
    stations = stations?.map { it.toBo() },
)

fun LocationDto.toBo() = Location(
    city = city,
    coordinates = Coordinates(latitude, longitude),
    country = country,
)

fun StationDto.toBo() = Station(
    id = id,
    name = name,
    timestamp = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }.parse(timestamp),
    freeBikes = freeBikes,
    emptySlots = emptySlots,
    coordinates = Coordinates(latitude, longitude),
)