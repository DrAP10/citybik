package com.base.usecases.di

import com.base.data.di.dataModule
import com.base.usecases.GetNetworksUseCase
import com.base.usecases.GetNetworkDetailUseCase
import com.base.usecases.GetNetworksLocalUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun useCasesModule() = module {
    includes(dataModule())

    singleOf(::GetNetworksUseCase)
    singleOf(::GetNetworkDetailUseCase)
    singleOf(::GetNetworksLocalUseCase)
}