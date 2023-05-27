package com.base.data.di

import com.base.data.GreetingRepository
import org.koin.dsl.module

fun dataModule() = module {
    single { GreetingRepository() }
}