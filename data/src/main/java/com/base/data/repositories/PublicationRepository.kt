package com.base.data.repositories

import com.base.data.model.local.PublicationPojo
import com.base.data.model.remote.PublicationDto
import com.base.data.sources.local.PublicationLocalDataSource
import com.base.data.sources.remote.PublicationRemoteDataSource
import com.base.domain.Answer
import com.base.domain.Answer.Loading.asSuccess
import com.base.domain.Publication
import java.text.SimpleDateFormat
import java.util.*

class PublicationRepository(
    private val remote: PublicationRemoteDataSource,
    private val local: PublicationLocalDataSource,
) {
    suspend fun getPublications(code: Int): Answer<Publication> {
        val answer = remote.getPublications(code).mapToDomain {
            it.toBo()
        }
        if (answer is Answer.Success) {
            local.insertPublications(answer.data.toPojo())
        }
        return answer
    }

    suspend fun getLocalPublications(): Answer<List<Publication>> =
        local.getPublications().map { it.toBo() }.asSuccess()

    suspend fun resetLocalPublications() =
        local.deleteAll()

}

@Throws
fun PublicationDto.toBo() = Publication(
    id = this.id,
    title = this.title,
    subTitle = this.subtitle,
    author = this.author,
    body = this.body,
    imageUrl = this.imageUrl,
    date = SimpleDateFormat("dd-MM-yyyy").parse(this.date),
    likes = this.likes,
)

fun PublicationPojo.toBo() = Publication(
    id = this.id,
    title = this.title,
    subTitle = this.subTitle,
    author = this.author,
    body = this.body,
    imageUrl = this.imageUrl,
    date = Date(this.date),
    likes = this.likes,
)

fun Publication.toPojo() = PublicationPojo(
    id = this.id,
    title = this.title,
    subTitle = this.subTitle,
    author = this.author,
    body = this.body,
    imageUrl = this.imageUrl,
    date = this.date.time,
    likes = this.likes,
)