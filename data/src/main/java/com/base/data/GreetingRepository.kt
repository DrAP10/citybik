package com.base.data

import com.base.domain.Greeting

class GreetingRepository {
    fun getGreeting(language: String): Greeting =
        Greeting(
            value = when (language) {
                "es" -> "Hola mundo"
                else -> "Hello world"
            }
        )
}