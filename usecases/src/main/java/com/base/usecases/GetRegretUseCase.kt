package com.base.usecases

import com.base.data.GreetingRepository

class GetRegretUseCase(private val repository: GreetingRepository) {

    fun getRegret(language: String) = repository.getGreeting(language)

}