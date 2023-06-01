package com.base.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.base.datasource.local.publications.PublicationDao
import com.base.datasource.local.publications.PublicationEntity

@Database(entities = [PublicationEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun publicationDao(): PublicationDao
}