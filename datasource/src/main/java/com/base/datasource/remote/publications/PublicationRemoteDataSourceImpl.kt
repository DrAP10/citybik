package com.base.datasource.remote.publications

import com.base.data.sources.remote.PublicationRemoteDataSource
import com.base.datasource.remote.base.wrapApiCall
import com.base.domain.Answer
import com.base.domain.Publication
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.text.SimpleDateFormat
import java.util.*

class PublicationRemoteDataSourceImpl(
    private val publicationApi: PublicationApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PublicationRemoteDataSource {


    override suspend fun getPublications(code: Int): Answer<Publication> = wrapApiCall(dispatcher) {
        publicationApi.getPublications(code).toBo()
    }

}

@Throws
fun PublicationDto.toBo() = Publication(
    id = this.id,
    title = this.title,
    subTitle = this.subtitle,
    author = this.author,
    body = this.body,
    imageUrl = this.imageUrl,
    date = SimpleDateFormat("dd-MM-yyyy", Locale.US).parse(this.date),
    likes = this.likes,
)