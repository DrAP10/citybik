package com.base.data.repositories

import com.base.data.model.PublicationDto
import com.base.data.model.PublicationsDto
import com.base.domain.Answer
import com.base.data.sources.remote.PublicationRemoteDataSource
import com.base.domain.Publication

class PublicationRepository(private val remote: PublicationRemoteDataSource) {
    suspend fun getPublications(teamId: Int): Answer<List<Publication>> {
        val answer = remote.getPublications(teamId)
        return answer.mapToDomain {
            it.toBo()
        }
    }
}

fun PublicationsDto.toBo() = this.publications.map { it.toBo() }
fun PublicationDto.toBo() = Publication(
    title = this.title
)