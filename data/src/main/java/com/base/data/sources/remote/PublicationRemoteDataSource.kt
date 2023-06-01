package com.base.data.sources.remote

import com.base.domain.Answer
import com.base.domain.Publication

interface PublicationRemoteDataSource {

    suspend fun getPublications(code: Int): Answer<Publication>

}