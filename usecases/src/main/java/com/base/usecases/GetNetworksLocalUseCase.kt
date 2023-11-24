package com.base.usecases

import com.base.data.repositories.NetworkRepository
import com.base.domain.Network

class GetNetworksLocalUseCase(
    private val repository: NetworkRepository
) : FlowUseCase<String, List<Network>>() {

    override suspend fun run(term: String) =
        repository.getLocalNetworks(term)

}