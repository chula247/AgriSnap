package com.chula.agrisnap.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chula.agrisnap.model.Dairy

@Dao
interface DairyDao {

    // Method to get all dairies from the database
    @Query("SELECT * FROM dairy")
    fun getAllDairies(): LiveData<List<Dairy>>

    // Method to insert a dairy item
    @Insert
    suspend fun insertDairy(dairy: Dairy)

    // Method to update a dairy item
    @Update
    suspend fun updateDairy(dairy: Dairy)

    // Method to delete a dairy item
    @Delete
    suspend fun deleteDairy(dairy: Dairy)
}