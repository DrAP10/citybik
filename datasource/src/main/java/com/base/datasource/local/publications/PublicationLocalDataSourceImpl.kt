package com.base.datasource.local.publications

import com.base.data.model.local.PublicationPojo
import com.base.data.sources.local.PublicationLocalDataSource

class PublicationLocalDataSourceImpl(
    private val publicationDao: PublicationDao
) : PublicationLocalDataSource {


    override suspend fun getPublications(): List<PublicationPojo> =
        publicationDao.getPublications().map { it.toPojo() }

    override suspend fun getPublication(id: Long): PublicationPojo =
        publicationDao.getPublication(id).toPojo()

    override suspend fun insertPublications(publication: PublicationPojo) =
        publicationDao.insertPublications(publication.toEntity())

    override suspend fun deleteAll() =
        publicationDao.deleteAll()

}

private fun PublicationPojo.toEntity() = PublicationEntity(
    id = this.id,
    title = this.title,
    subTitle = this.subTitle,
    author = this.author,
    body = this.body,
    imageUrl = this.imageUrl,
    date = this.date,
    likes = this.likes,
)

private fun PublicationEntity.toPojo() = PublicationPojo(
    id = this.id,
    title = this.title,
    subTitle = this.subTitle,
    author = this.author,
    body = this.body,
    imageUrl = this.imageUrl,
    date = this.date,
    likes = this.likes,
)