package com.base.data.repositories

import com.base.data.sources.local.PublicationLocalDataSource
import com.base.data.sources.remote.PublicationRemoteDataSource
import com.base.domain.Answer
import com.base.domain.Answer.Loading.asError
import com.base.domain.Answer.Loading.asSuccess
import com.base.domain.Publication
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PublicationRepository(
    private val remote: PublicationRemoteDataSource,
    private val local: PublicationLocalDataSource,
) {
    suspend fun getPublications(code: Int): Answer<Publication> {
        val answer = try {
            val answer = remote.getPublications(code)
            if (answer is Answer.Success) {
                local.insertPublications(answer.data)
            }
            answer
        } catch (ex: Exception) {
            ex.asError<Publication>()
        }
        return answer
    }

    suspend fun getLocalPublications(): Flow<Answer<List<Publication>>> =
        local.getPublications().map { list -> list.map { item -> item }.asSuccess() }

    suspend fun resetLocalPublications() =
        local.deleteAll()

}