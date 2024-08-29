package com.project.deteksimata.room

import android.app.Application

class HistoryRepository(private val historyDao: HistoryDao) {

    suspend fun saveHistory(historyItem: HistoryCacheEntity) {
        historyDao.insertHistory(historyItem)
    }

    suspend fun getAllHistory(): List<HistoryCacheEntity> {
        return historyDao.getHistory()
    }
}
