package com.base.datasource.remote

import com.base.data.model.PublicationDto
import com.base.data.model.base.DataAnswer
import com.base.data.sources.remote.PublicationRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PublicationRemoteDataSourceImpl(
    private val publicationApi: PublicationApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PublicationRemoteDataSource {


    override suspend fun getPublications(code: Int): DataAnswer<PublicationDto> = wrapApiCall(dispatcher) {
        publicationApi.getPublications(code)
    }

}