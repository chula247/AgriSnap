package com.chula.agrisnap.repository

import android.content.Context
import com.chula.agrisnap.data.VegetableDatabase
import com.chula.agrisnap.model.Vegetable

class VegetableRepository(context: Context) {
    private val vegetableDao = VegetableDatabase.getDatabase(context).vegetableDao()

    suspend fun insertVegetable(vegetable: Vegetable) {
        vegetableDao.insertVegetable(vegetable)
    }

    fun getAllVegetables() = vegetableDao.getAllVegetables()

    suspend fun deleteVegetable(vegetable: Vegetable) = vegetableDao.deleteVegetable(vegetable)
}