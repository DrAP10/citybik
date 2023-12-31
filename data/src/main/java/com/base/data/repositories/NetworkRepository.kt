package com.base.data.repositories

import com.base.data.sources.local.NetworkLocalDataSource
import com.base.data.sources.remote.NetworkRemoteDataSource
import com.base.domain.Answer
import com.base.domain.Answer.Loading.asError
import com.base.domain.Answer.Loading.asErrorWithLocalData
import com.base.domain.Answer.Loading.asSuccess
import com.base.domain.Network
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

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

    suspend fun getNetworkDetail(networkId: String): Flow<Answer<Network>> = flow {
        try {
            val answer = remote.getNetwork(networkId)
            if (answer is Answer.Success) {
                emit(answer)
                local.insertOrUpdateNetwork(answer.data)
            } else {
                emitAll(
                    local.getNetworkDetail(networkId)
                        .map { it?.asErrorWithLocalData() ?: answer }
                )
            }
        } catch (ex: Exception) {
            emitAll(
                local.getNetworkDetail(networkId)
                    .map { it?.asErrorWithLocalData() ?: ex.asError<Network>() }
            )
        }
    }

    suspend fun getLocalNetworks(term: String): Flow<Answer<List<Network>>> =
        local.getNetworks(term).map { list -> list.map { item -> item }.asSuccess() }

    suspend fun resetLocalNetworks() =
        local.deleteAll()

}