package com.project.deteksimata.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [HistoryCacheEntity::class], version = 1)
abstract class HistoryDatabase : RoomDatabase(){
    abstract fun historyDao() : HistoryDao
    companion object{
        const val DATABASE_NAME : String = "history_db"
        private var instance : HistoryDatabase? = null

        fun getInstance(ctx : Context) : HistoryDatabase{
            if(instance == null){
                instance = Room.databaseBuilder(ctx.applicationContext, HistoryDatabase::class.java, DATABASE_NAME).fallbackToDestructiveMigration().build()
            }

            return instance!!
        }
    }
}