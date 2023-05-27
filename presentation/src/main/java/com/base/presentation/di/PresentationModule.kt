package com.base.presentation.di

import com.base.usecases.di.useCasesModule
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.base.presentation.home.HomeViewModel

fun presentationModule() = module {
    includes(useCasesModule())

    viewModelOf(::HomeViewModel)

}