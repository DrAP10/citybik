package com.base.data.sources.remote

import com.base.data.model.GreetingDto

interface GreetingRemoteDataSource {

    fun getGreeting(language: String): GreetingDto

}