package com.base.datasource.remote
import com.base.data.model.PublicationsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PublicationApi {

    @GET("{teamId}/publications")
    suspend fun getPublications(@Path("teamId") teamId: Int): PublicationsDto

}