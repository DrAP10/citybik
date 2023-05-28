package com.base.datasource.remote
import com.base.data.model.PublicationsDto
import com.base.data.sources.remote.PublicationRemoteDataSource
import retrofit2.http.GET
import retrofit2.http.Path

interface PublicationRemoteDataSourceImpl : PublicationRemoteDataSource {
//    override fun getGreeting(language: String): GreetingDto {
//        return GreetingDto(
//            text = when (language) {
//                "es" -> "Hola"
//                "en" -> "Hello"
//                "it" -> "Ciao"
//                else -> "Io k c"
//            }
//        )
//    }

    @GET("{teamId}/publications")
    override suspend fun getPublications(@Path("teamId") teamId: Int): PublicationsDto
}