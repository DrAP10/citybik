package com.base.data.repositories

import com.base.data.model.GreetingDto
import com.base.data.sources.remote.GreetingRemoteDataSource
import com.base.domain.Greeting

class GreetingRepository(private val remote: GreetingRemoteDataSource) {
    fun getGreeting(language: String): Greeting =
        remote.getGreeting(language).toBo()

}

fun GreetingDto.toBo() = Greeting(
    value = this.text
)