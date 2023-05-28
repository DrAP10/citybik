package com.base.data.repositories

import com.base.data.model.PublicationDto
import com.base.data.sources.remote.PublicationRemoteDataSource
import com.base.domain.Publication

class PublicationRepository(private val remote: PublicationRemoteDataSource) {
    suspend fun getPublications(teamId: Int): List<Publication> =
        remote.getPublications(teamId).publications.map { it.toBo() }

}

fun PublicationDto.toBo() = Publication(
    title = this.title
)