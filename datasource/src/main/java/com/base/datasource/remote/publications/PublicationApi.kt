package com.base.datasource.remote.publications
import com.base.data.model.remote.PublicationDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PublicationApi {

    @GET("/fake/{code}")
    suspend fun getPublications(@Path("code") code: Int): PublicationDto

}