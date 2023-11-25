package com.base.usecases.di

import com.base.data.di.dataModule
import com.base.usecases.RequestNetworksUseCase
import com.base.usecases.GetNetworkDetailUseCase
import com.base.usecases.GetNetworksByTermUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun useCasesModule() = module {
    includes(dataModule())

    singleOf(::RequestNetworksUseCase)
    singleOf(::GetNetworkDetailUseCase)
    singleOf(::GetNetworksByTermUseCase)
}