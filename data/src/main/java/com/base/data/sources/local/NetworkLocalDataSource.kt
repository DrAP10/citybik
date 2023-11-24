package com.base.data.sources.local

import com.base.domain.Network
import kotlinx.coroutines.flow.Flow


interface NetworkLocalDataSource {
    suspend fun insertOrUpdateNetworks(networks: List<Network>)
    suspend fun insertOrUpdateNetwork(network: Network)
    suspend fun getNetworks(term: String): Flow<List<Network>>
    suspend fun deleteAll()
}