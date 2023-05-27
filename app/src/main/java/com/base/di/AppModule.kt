package com.base.di

import com.base.presentation.di.presentationModule
import org.koin.dsl.module

fun appModule() = module {
    includes(presentationModule())

}