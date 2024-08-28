package com.project.deteksimata.sharedPreference

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HistoryPreference(private val context: Context) {

    private val gson = Gson()
    private val historyPreference = context.getSharedPreferences("historyPref", Context.MODE_PRIVATE)

    fun saveHistory(data:ArrayList<History>, key:String){
        val json = gson.toJson(data)
        historyPreference.edit().putString(key,json).apply()

    }

    fun getHistory(key: String) : ArrayList<History>{
        val json = historyPreference.getString(key,null)
        val type = object : TypeToken<ArrayList<History>>(){}.type
        return gson.fromJson(json,type) ?: ArrayList()
    }


}
data class History(
    val filename: String,
    val result: String
)