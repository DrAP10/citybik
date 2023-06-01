package com.base.datasource.local.publications

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PublicationDao {
    @Query("SELECT * FROM PUBLICATION")
    fun getPublications(): Flow<List<PublicationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPublications(vararg publications: PublicationEntity)

    @Query("DELETE FROM PUBLICATION")
    suspend fun deleteAll()
}