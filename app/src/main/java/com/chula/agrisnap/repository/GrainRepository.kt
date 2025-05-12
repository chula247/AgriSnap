package com.chula.agrisnap.repository

import android.content.Context
import com.chula.agrisnap.data.AppDatabase
import com.chula.agrisnap.data.GrainDatabase
import com.chula.agrisnap.model.Grain

class GrainRepository(context: Context) {
    private val grainDao = GrainDatabase.getDatabase(context).grainDao()

    // Insert a Grain item
    suspend fun insertGrain(grain: Grain) {
        grainDao.insertGrain(grain)
    }

    // Get all Grain items
    fun getAllGrains() = grainDao.getAllGrains()

    // Delete a Grain item
    suspend fun deleteGrain(grain: Grain) {
        grainDao.deleteGrain(grain)
    }
}
