package com.project.deteksimata.room

import androidx.room.*

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history_tbl")
    suspend fun getHistory(): List<HistoryCacheEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(historyCacheEntity: HistoryCacheEntity): Long
}
