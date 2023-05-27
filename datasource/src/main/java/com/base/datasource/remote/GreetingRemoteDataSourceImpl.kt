package com.base.datasource.remote

import com.base.data.model.GreetingDto
import com.base.data.sources.remote.GreetingRemoteDataSource

class GreetingRemoteDataSourceImpl : GreetingRemoteDataSource {
    override fun getGreeting(language: String): GreetingDto {
        return GreetingDto(
            text = when (language) {
                "es" -> "Hola"
                "en" -> "Hello"
                "it" -> "Ciao"
                else -> "Io k c"
            }
        )
    }
}