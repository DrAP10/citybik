package com.base.usecases

import com.base.data.repositories.PublicationRepository
import com.base.domain.Answer.Loading.asSuccess

class ResetLocalPublicationsUseCase(
    private val repository: PublicationRepository
) : UseCaseParamLess<Unit>() {

    override suspend fun run() =
        repository.resetLocalPublications().asSuccess()

}