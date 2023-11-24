package com.base.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.base.datasource.local.networks.NetworksDao
import com.base.datasource.local.networks.NetworkEntity
import com.base.datasource.local.networks.StationEntity

@Database(entities = [NetworkEntity::class, StationEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun networksDao(): NetworksDao
}