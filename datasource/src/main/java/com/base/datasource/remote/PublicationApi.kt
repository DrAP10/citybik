package com.base.datasource.remote
import com.base.data.model.PublicationDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PublicationApi {

    @GET("/fake/{code}")
    suspend fun getPublications(@Path("code") code: Int): PublicationDto

}