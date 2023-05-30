package com.base.usecases

import com.base.data.repositories.PublicationRepository
import com.base.domain.Answer
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class GetPublicationsUseCase(private val repository: PublicationRepository) {

    suspend fun run(code: Int) = flow {
        emit(Answer.Loading)
        delay(2000)
        emit(repository.getPublications(code))
    }

}