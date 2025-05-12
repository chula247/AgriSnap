package com.chula.agrisnap.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chula.agrisnap.model.Vegetable

@Dao
interface VegetableDao {
    @Query("SELECT * FROM vegetables")
    fun getAllVegetables(): LiveData<List<Vegetable>>

    @Insert
    suspend fun insertVegetable(vegetable: Vegetable)

    @Update
    suspend fun updateVegetable(vegetable: Vegetable)

    @Delete
    suspend fun deleteVegetable(vegetable: Vegetable)
}
