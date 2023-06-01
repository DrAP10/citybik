package com.base.datasource.local.di

import android.app.Application
import androidx.room.Room
import com.base.data.sources.local.PublicationLocalDataSource
import com.base.datasource.local.AppDatabase
import com.base.datasource.local.publications.PublicationDao
import com.base.datasource.local.publications.PublicationLocalDataSourceImpl
import org.koin.dsl.module

fun localDataSourceModule() = module {
    single { provideDataBase(get()) }
    factory { providePublicationDao(get()) }
    factory { providePublicationLocalDataSource(get()) }
}

fun provideDataBase(app: Application) = Room.databaseBuilder(
    app.applicationContext,
    AppDatabase::class.java,
    "Tasks.db"
).build()

fun providePublicationDao(database: AppDatabase) = database.publicationDao()

fun providePublicationLocalDataSource(publicationDao: PublicationDao): PublicationLocalDataSource =
    PublicationLocalDataSourceImpl(publicationDao)
