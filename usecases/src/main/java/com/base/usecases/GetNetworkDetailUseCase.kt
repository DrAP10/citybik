package com.base.usecases

import com.base.data.repositories.NetworkRepository
import com.base.domain.Network

class GetNetworkDetailUseCase(
    private val repository: NetworkRepository
) : FlowUseCase<String, Network>() {

    override suspend fun run(networkId: String) =
        repository.getNetworkDetail(networkId)

}