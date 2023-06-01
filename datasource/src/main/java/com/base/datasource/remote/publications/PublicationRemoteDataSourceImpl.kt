package com.base.datasource.remote.publications

import com.base.data.model.base.DataAnswer
import com.base.data.model.remote.PublicationDto
import com.base.data.sources.remote.PublicationRemoteDataSource
import com.base.datasource.remote.wrapApiCall
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