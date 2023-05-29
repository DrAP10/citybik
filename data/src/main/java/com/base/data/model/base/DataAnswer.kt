package com.base.data.model.base

import com.base.domain.Answer
import java.io.IOException

sealed class DataAnswer<out D> {

    data class Success<D>(val data: D) : DataAnswer<D>()

    data class Error(val code: Int, val message: String) : DataAnswer<Nothing>()

    data class NetworkError(val error: IOException) : DataAnswer<Nothing>()

    data class UnknownError(val exception: Throwable?) : DataAnswer<Nothing>()

    fun <O> mapToDomain(mapData: (D) -> O) =
        when(this) {
            is Success -> Answer.Success(mapData(this.data))
            is Error -> Answer.Error(this.code, this.message)
            is NetworkError -> Answer.NetworkError(this.error)
            is UnknownError -> Answer.UnknownError(this.exception)
        }
}