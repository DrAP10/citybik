package com.base.datasource.remote.di

import android.content.Context
import com.base.data.sources.remote.NetworkRemoteDataSource
import com.base.datasource.BuildConfig
import com.base.datasource.remote.networks.NetworkRemoteDataSourceImpl
import com.base.datasource.remote.networks.NetworksApi
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun remoteDataSourceModule() = module {
    factory { provideOkHttpClient(androidContext()) }
    single { provideRetrofit(get()) }
    factory { provideNetworkApi(get()) }
    factory { provideNetworkRemoteDataSource(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(context: Context): OkHttpClient {
    return OkHttpClient().newBuilder()
        .addInterceptor(ChuckerInterceptor(context))
        .build()
}

fun provideNetworkApi(retrofit: Retrofit): NetworksApi = retrofit.create(NetworksApi::class.java)

fun provideNetworkRemoteDataSource(networksApi: NetworksApi): NetworkRemoteDataSource =
    NetworkRemoteDataSourceImpl(networksApi)