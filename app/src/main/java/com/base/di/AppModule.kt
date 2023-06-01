package com.base.di

import com.base.datasource.local.di.localDataSourceModule
import com.base.datasource.remote.di.remoteDataSourceModule
import com.base.presentation.di.presentationModule
import org.koin.dsl.module

fun appModule() = module {
    includes(localDataSourceModule())
    includes(remoteDataSourceModule())
    includes(presentationModule())
}