package com.base.datasource.local.publications

import com.base.data.sources.local.PublicationLocalDataSource
import com.base.domain.Publication
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class PublicationLocalDataSourceImpl(
    private val publicationDao: PublicationDao
) : PublicationLocalDataSource {

    override suspend fun getPublications(): Flow<List<Publication>> =
        publicationDao.getPublications().map { list -> list.map { item -> item.toBo() } }

    override suspend fun insertPublications(publication: Publication) =
        publicationDao.insertPublications(publication.toEntity())

    override suspend fun deleteAll() =
        publicationDao.deleteAll()

}

private fun Publication.toEntity() = PublicationEntity(
    id = this.id,
    title = this.title,
    subTitle = this.subTitle,
    author = this.author,
    body = this.body,
    imageUrl = this.imageUrl,
    date = this.date.time,
    likes = this.likes,
)

private fun PublicationEntity.toBo() = Publication(
    id = this.id,
    title = this.title,
    subTitle = this.subTitle,
    author = this.author,
    body = this.body,
    imageUrl = this.imageUrl,
    date = Date(this.date),
    likes = this.likes,
)