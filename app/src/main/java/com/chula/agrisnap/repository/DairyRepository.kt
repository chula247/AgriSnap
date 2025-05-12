package com.chula.agrisnap.repository


import android.content.Context
import com.chula.agrisnap.data.AppDatabase
import com.chula.agrisnap.model.Dairy

class DairyRepository(context: Context) {
    private val dairyDao = AppDatabase.getDatabase(context).dairyDao()

    // Insert a Dairy item
    suspend fun insertDairy(dairy: Dairy) {
        dairyDao.insertDairy(dairy)
    }

    // Get all Dairy items
    fun getAllDairies() = dairyDao.getAllDairies()

    // Delete a Dairy item
    suspend fun deleteDairy(dairy: Dairy) {
        dairyDao.deleteDairy(dairy)
    }
}