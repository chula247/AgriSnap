package com.chula.agrisnap.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chula.agrisnap.model.Grain

@Dao
interface GrainDao {

    // Method to get all grains from the database
    @Query("SELECT * FROM grain")
    fun getAllGrains(): LiveData<List<Grain>>

    // Method to insert a grain item
    @Insert
    suspend fun insertGrain(grain: Grain)

    // Method to update a grain item
    @Update
    suspend fun updateGrain(grain: Grain)

    // Method to delete a grain item
    @Delete
    suspend fun deleteGrain(grain: Grain)
}
