package com.base.usecases

import com.base.data.repositories.PublicationRepository
import com.base.domain.Publication

class GetPublicationsUseCase(
    private val repository: PublicationRepository
) : UseCase<Int, Publication>() {

    override suspend fun run(code: Int) =
        repository.getPublications(code)

}