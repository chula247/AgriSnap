package com.chula.agrisnap.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chula.agrisnap.model.Fruit

@Dao
interface FruitDao {
    @Query("SELECT * FROM fruits")
    fun getAllFruits(): LiveData<List<Fruit>>

    @Insert
    suspend fun insertFruit(fruit: Fruit)

    @Update
    suspend fun updateFruit(fruit: Fruit)

    @Delete
    suspend fun deleteFruit(fruit: Fruit)
}
