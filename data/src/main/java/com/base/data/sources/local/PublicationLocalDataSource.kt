package com.base.data.sources.local

import com.base.data.model.local.PublicationPojo


interface PublicationLocalDataSource {
    suspend fun getPublications(): List<PublicationPojo>
    suspend fun getPublication(id: Long): PublicationPojo
    suspend fun insertPublications(publication: PublicationPojo)
    suspend fun deleteAll()
}