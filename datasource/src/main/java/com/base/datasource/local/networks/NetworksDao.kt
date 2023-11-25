package com.base.datasource.local.networks

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class NetworksDao {

    @Transaction
    @Query("SELECT * FROM NETWORK WHERE name LIKE '%' || :term || '%' ORDER BY name ASC")
    abstract fun getNetworks(term: String): Flow<List<NetworksWithStations>>

    @Transaction
    @Query("SELECT * FROM NETWORK WHERE id = :networkId")
    abstract fun getNetworkDetail(networkId: String): Flow<NetworksWithStations?>

    suspend fun insertNetworksWithStations(vararg networksWithStations: NetworksWithStations) {
        for (networkWithStations in networksWithStations) {
            for (station in networkWithStations.stations) {
                insertStation(station)
            }
            insertNetwork(networkWithStations.network)
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertNetwork(network: NetworkEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertStation(station: StationEntity)

    @Query("DELETE FROM NETWORK")
    abstract suspend fun deleteAll()
}