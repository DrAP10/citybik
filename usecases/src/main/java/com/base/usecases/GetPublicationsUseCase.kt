package com.base.usecases

import com.base.data.repositories.PublicationRepository

class GetPublicationsUseCase(private val repository: PublicationRepository) {

    suspend fun getPublications(teamId: Int) = repository.getPublications(teamId)

}