package com.base.data.sources.remote

import com.base.data.model.PublicationsDto
import com.base.data.model.base.DataAnswer

interface PublicationRemoteDataSource {

    suspend fun getPublications(teamId: Int): DataAnswer<PublicationsDto>

}