package com.base.presentation.di

import com.base.usecases.di.useCasesModule
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.base.presentation.features.splash.SplashViewModel
import com.base.presentation.features.list.NetworksListViewModel
import com.base.presentation.features.details.NetworkDetailViewModel

fun presentationModule() = module {
    includes(useCasesModule())

    viewModelOf(::SplashViewModel)
    viewModelOf(::NetworksListViewModel)
    viewModelOf(::NetworkDetailViewModel)

}