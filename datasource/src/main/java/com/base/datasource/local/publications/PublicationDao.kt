package com.base.datasource.local.publications

import androidx.room.*

@Dao
interface PublicationDao {
    @Query("SELECT * FROM PUBLICATION")
    suspend fun getPublications(): List<PublicationEntity>

    @Query("SELECT * FROM PUBLICATION WHERE id = :id LIMIT 1")
    suspend fun getPublication(id: Long): PublicationEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPublications(vararg publications: PublicationEntity)

    @Query("DELETE FROM PUBLICATION")
    suspend fun deleteAll()
}