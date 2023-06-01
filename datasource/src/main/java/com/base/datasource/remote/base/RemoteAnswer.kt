package com.base.datasource.remote.base

import com.base.domain.Answer
import java.io.IOException

sealed class RemoteAnswer<out D> {

    data class Success<D>(val data: D) : RemoteAnswer<D>()

    data class Error(val code: Int, val message: String) : RemoteAnswer<Nothing>()

    data class NetworkError(val error: IOException) : RemoteAnswer<Nothing>()

    data class UnknownError(val exception: Throwable?) : RemoteAnswer<Nothing>()

    fun <O> mapToDomain() =
        when(this) {
            is Success -> Answer.Success(this.data)
            is Error -> Answer.Error(this.code, this.message)
            is NetworkError -> Answer.NetworkError(this.error)
            is UnknownError -> Answer.UnknownError(this.exception)
        }
}