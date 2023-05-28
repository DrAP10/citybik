package com.base.data.sources.remote

import com.base.data.model.PublicationsDto

interface PublicationRemoteDataSource {

    suspend fun getPublications(teamId: Int): PublicationsDto

}