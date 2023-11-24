package com.base.datasource.remote.networks

import com.base.datasource.remote.networks.model.NetworkDto
import com.base.datasource.remote.networks.model.NetworksDto
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworksApi {

    @GET("networks?fields=id,name,location")
    suspend fun getNetworks(): NetworksDto

    @GET("networks/{networkId}?fields=id,name,location,stations")
    suspend fun getNetwork(@Path("networkId") networkId: String): NetworkResponseDto

}

data class NetworkResponseDto(
    val network: NetworkDto
)
