package com.base.data.di

import com.base.data.repositories.PublicationRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun dataModule() = module {
    singleOf(::PublicationRepository)
}