package com.base.usecases

import com.base.data.repositories.PublicationRepository
import com.base.domain.Answer
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class GetPublicationsUseCase(private val repository: PublicationRepository) {

    suspend fun run(teamId: Int) = flow {
        emit(Answer.Loading)
        delay(5000)
        emit(repository.getPublications(teamId))
    }

}