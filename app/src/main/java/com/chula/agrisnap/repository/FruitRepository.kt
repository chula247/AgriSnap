package com.chula.agrisnap.repository

import android.content.Context
import com.chula.agrisnap.data.AppDatabase
import com.chula.agrisnap.data.FruitDatabase
import com.chula.agrisnap.model.Fruit

class FruitRepository(context: Context) {
    private val fruitDao = FruitDatabase.getDatabase(context).fruitDao()

    // Insert a Fruit item
    suspend fun insertFruit(fruit: Fruit) {
        fruitDao.insertFruit(fruit)
    }

    // Get all Fruit items
    fun getAllFruits() = fruitDao.getAllFruits()

    // Delete a Fruit item
    suspend fun deleteFruit(fruit: Fruit) {
        fruitDao.deleteFruit(fruit)
    }
}
