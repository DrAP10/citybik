package com.base.datasource.remote.di

import com.base.data.sources.remote.PublicationRemoteDataSource
import com.base.datasource.BuildConfig
import com.base.datasource.remote.PublicationApi
import com.base.datasource.remote.PublicationRemoteDataSourceImpl
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun dataSourceModule() = module {
    factory { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    factory { providePublicationApi(get()) }
    factory { providePublicationRemoteDataSource(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().build()
}

fun providePublicationApi(retrofit: Retrofit): PublicationApi = retrofit.create(PublicationApi::class.java)

fun providePublicationRemoteDataSource(publicationApi: PublicationApi): PublicationRemoteDataSource =
    PublicationRemoteDataSourceImpl(publicationApi)