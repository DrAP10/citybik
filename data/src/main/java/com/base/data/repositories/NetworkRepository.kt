package com.base.data.repositories

import com.base.data.sources.local.NetworkLocalDataSource
import com.base.data.sources.remote.NetworkRemoteDataSource
import com.base.domain.Answer
import com.base.domain.Answer.Loading.asError
import com.base.domain.Answer.Loading.asErrorWithLocalData
import com.base.domain.Answer.Loading.asSuccess
import com.base.domain.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class NetworkRepository(
    private val remote: NetworkRemoteDataSource,
    private val local: NetworkLocalDataSource,
) {
    suspend fun getNetworks(): Answer<List<Network>> {
        val answer = try {
            val answer = remote.getNetworks()
            if (answer is Answer.Success) {
                local.deleteAll()
                local.insertOrUpdateNetworks(answer.data.sortedBy { it.name })
            }
            answer
        } catch (ex: Exception) {
            ex.asError<Network>()
        }
        return answer
    }

    suspend fun getNetworkDetail(networkId: String): Answer<Network> {
        return try {
            val answer = remote.getNetwork(networkId)
            if (answer is Answer.Success) {
                local.insertOrUpdateNetwork(answer.data)
                answer
            } else {
                val flow = local.getNetworkDetail(networkId)
                runBlocking(Dispatchers.IO) {
                    flow.first()?.asErrorWithLocalData() ?: answer
                }
//                local.getNetworkDetail(networkId)?.asErrorWithLocalData() ?: answer
            }
        } catch (ex: Exception) {
            val flow = local.getNetworkDetail(networkId)
            runBlocking(Dispatchers.IO) {
                flow.first()?.asErrorWithLocalData() ?: ex.asError<Network>()
            }
//            local.getNetworkDetail(networkId)?.asErrorWithLocalData() ?: ex.asError<Network>()
        }
    }

    suspend fun getLocalNetworks(term: String): Flow<Answer<List<Network>>> =
        local.getNetworks(term).map { list -> list.map { item -> item }.asSuccess() }

    suspend fun resetLocalNetworks() =
        local.deleteAll()

}