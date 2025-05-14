package com.chula.agrisnap.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chula.agrisnap.model.Offer

@Dao
interface OfferDao {

    // Method to get all offers from the database
    @Query("SELECT * FROM offer")
    fun getAllOffers(): LiveData<List<Offer>>

    // Method to insert a new offer
    @Insert
    suspend fun insertOffer(offer: Offer)

    // Method to update an existing offer
    @Update
    suspend fun updateOffer(offer: Offer)

    // Method to delete an offer
    @Delete
    suspend fun deleteOffer(offer: Offer)
}
