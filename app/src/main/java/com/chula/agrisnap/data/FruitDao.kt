package com.chula.agrisnap.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chula.agrisnap.model.Fruit

@Dao
interface FruitDao {

    // Method to get all fruits from the database
    @Query("SELECT * FROM fruit")
    fun getAllFruits(): LiveData<List<Fruit>>

    // Method to insert a fruit item
    @Insert
    suspend fun insertFruit(fruit: Fruit)

    // Method to update a fruit item
    @Update
    suspend fun updateFruit(fruit: Fruit)

    // Method to delete a fruit item
    @Delete
    suspend fun deleteFruit(fruit: Fruit)
}
