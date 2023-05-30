package com.base.data.repositories

import com.base.data.model.PublicationDto
import com.base.domain.Answer
import com.base.data.sources.remote.PublicationRemoteDataSource
import com.base.domain.Publication

class PublicationRepository(private val remote: PublicationRemoteDataSource) {
    suspend fun getPublications(code: Int): Answer<Publication> {
        val answer = remote.getPublications(code)
        return answer.mapToDomain {
            it.toBo()
        }
    }
}

fun PublicationDto.toBo() = Publication(
    message = this.message
)