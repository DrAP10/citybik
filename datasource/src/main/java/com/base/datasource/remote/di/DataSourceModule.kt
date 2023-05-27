package com.base.datasource.remote.di

import com.base.data.sources.remote.GreetingRemoteDataSource
import com.base.datasource.remote.GreetingRemoteDataSourceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun dataSourceModule() = module {
    singleOf<GreetingRemoteDataSource>(::GreetingRemoteDataSourceImpl)
}