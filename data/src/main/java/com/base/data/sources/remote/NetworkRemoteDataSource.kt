package com.base.data.sources.remote

import com.base.domain.Answer
import com.base.domain.Network

interface NetworkRemoteDataSource {

    suspend fun getNetworks(): Answer<List<Network>>

    suspend fun getNetwork(networkId: String): Answer<Network>

}