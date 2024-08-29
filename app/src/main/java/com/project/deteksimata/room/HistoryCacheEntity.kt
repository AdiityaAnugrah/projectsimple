package com.project.deteksimata.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_tbl")
data class HistoryCacheEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "filename") val filename: String,
    @ColumnInfo(name = "result") val result: String
)