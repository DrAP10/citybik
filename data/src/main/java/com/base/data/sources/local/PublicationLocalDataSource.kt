package com.base.data.sources.local

import com.base.domain.Publication
import kotlinx.coroutines.flow.Flow


interface PublicationLocalDataSource {
    suspend fun getPublications(): Flow<List<Publication>>
    suspend fun insertPublications(publication: Publication)
    suspend fun deleteAll()
}