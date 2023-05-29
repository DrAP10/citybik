package com.base.datasource.remote

import com.base.data.model.PublicationsDto
import com.base.data.model.base.DataAnswer
import com.base.data.sources.remote.PublicationRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PublicationRemoteDataSourceImpl(
    private val publicationApi: PublicationApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PublicationRemoteDataSource {


    override suspend fun getPublications(teamId: Int): DataAnswer<PublicationsDto> = wrapApiCall(dispatcher) {
        publicationApi.getPublications(teamId)
    }

}