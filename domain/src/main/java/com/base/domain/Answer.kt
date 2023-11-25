package com.base.domain

import java.io.IOException

sealed class Answer<out D> {
    data class Success<D>(val data: D) : Answer<D>()

    data class ErrorWithLocalData<D>(val data: D) : Answer<D>()

    data class Error(val code: Int, val message: String) : Answer<Nothing>()

    data class NetworkError(val error: IOException) : Answer<Nothing>()

    data class UnknownError(val exception: Throwable?) : Answer<Nothing>()

    object Loading : Answer<Nothing>()

    fun <D> D.asSuccess() = Success(this)
    fun <D> D.asErrorWithLocalData() = ErrorWithLocalData(this)
    fun <D> Throwable.asError() = UnknownError(this)

}