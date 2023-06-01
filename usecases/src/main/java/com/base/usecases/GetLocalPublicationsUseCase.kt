package com.base.usecases

import com.base.data.repositories.PublicationRepository
import com.base.domain.Answer.Loading.asSuccess
import com.base.domain.Publication

class GetLocalPublicationsUseCase(
    private val repository: PublicationRepository
) : UseCaseParamLess<List<Publication>>() {

    override suspend fun run() =
        repository.getLocalPublications()

}