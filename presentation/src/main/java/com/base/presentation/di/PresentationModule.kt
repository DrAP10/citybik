package com.base.presentation.di

import com.base.usecases.di.useCasesModule
import org.koin.dsl.module

fun presentationModule() = module {
    includes(useCasesModule())

}