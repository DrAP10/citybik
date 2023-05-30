package com.base.usecases

import com.base.domain.Answer
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class UseCase<in Params, out Result> {
    abstract suspend fun run(params: Params): Answer<Result>

    operator fun invoke(params: Params): Flow<Answer<Result>> =
        flow {
            emit(Answer.Loading)
            delay(2000)
            emit(run(params))
        }

}

abstract class UseCaseParamLess<out Result> {
    abstract suspend fun run(): Answer<Result>

    operator fun invoke(): Flow<Answer<Result>> =
        flow {
            emit(Answer.Loading)
            delay(2000)
            emit(run())
        }

}