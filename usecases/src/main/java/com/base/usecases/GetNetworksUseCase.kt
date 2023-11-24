package com.base.usecases

import com.base.data.repositories.NetworkRepository
import com.base.domain.Network

class GetNetworksUseCase(
    private val repository: NetworkRepository
) : UseCaseParamLess<List<Network>>() {

    override suspend fun run() =
        repository.getNetworks()

}