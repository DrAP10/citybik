package com.base.datasource.remote

import com.base.data.model.base.DataAnswer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> wrapApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): DataAnswer<T> {
    return withContext(dispatcher) {
        try {
            DataAnswer.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> DataAnswer.NetworkError(throwable)
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    DataAnswer.Error(code, errorResponse)
                }
                else -> {
                    DataAnswer.UnknownError(throwable)
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): String {
    return try {
        throwable.response()?.errorBody()?.string() ?: ""
    } catch (exception: Exception) {
        ""
    }
}