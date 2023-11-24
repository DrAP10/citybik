package com.base.datasource.local.di

import android.app.Application
import androidx.room.Room
import com.base.data.sources.local.NetworkLocalDataSource
import com.base.datasource.local.AppDatabase
import com.base.datasource.local.networks.NetworkLocalDataSourceImpl
import com.base.datasource.local.networks.NetworksDao
import org.koin.dsl.module

fun localDataSourceModule() = module {
    single { provideDataBase(get()) }
    factory { provideNetworksDao(get()) }
    factory { provideNetworkLocalDataSource(get()) }
}

fun provideDataBase(app: Application) = Room.databaseBuilder(
    app.applicationContext,
    AppDatabase::class.java,
    "Tasks.db"
).build()

fun provideNetworksDao(database: AppDatabase) = database.networksDao()

fun provideNetworkLocalDataSource(networksDao: NetworksDao): NetworkLocalDataSource =
    NetworkLocalDataSourceImpl(networksDao)
