package com.base.data.sources.remote

import com.base.data.model.PublicationDto
import com.base.data.model.base.DataAnswer

interface PublicationRemoteDataSource {

    suspend fun getPublications(code: Int): DataAnswer<PublicationDto>

}