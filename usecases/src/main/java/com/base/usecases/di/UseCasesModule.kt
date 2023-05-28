package com.base.usecases.di

import com.base.data.di.dataModule
import com.base.usecases.GetPublicationsUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun useCasesModule() = module {
    includes(dataModule())

    singleOf(::GetPublicationsUseCase)
}