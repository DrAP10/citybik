package com.base.datasource.remote.base

import com.base.domain.Answer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> wrapApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): Answer<T> {
    return withContext(dispatcher) {
        val dataAnswer = try {
            RemoteAnswer.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> RemoteAnswer.NetworkError(throwable)
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    RemoteAnswer.Error(code, errorResponse)
                }
                else -> {
                    RemoteAnswer.UnknownError(throwable)
                }
            }
        }
        dataAnswer.mapToDomain<T>()
    }
}

private fun convertErrorBody(throwable: HttpException): String {
    return try {
        throwable.response()?.errorBody()?.string() ?: ""
    } catch (exception: Exception) {
        ""
    }
}