package com.chula.agrisnap.repository

import android.content.Context
import com.chula.agrisnap.data.OfferDatabase
import com.chula.agrisnap.model.Offer

class OfferRepository(context: Context) {
    private val offerDao = OfferDatabase.getDatabase(context).offerDao()

    // Insert an Offer item
    suspend fun insertOffer(offer: Offer) {
        offerDao.insertOffer(offer)
    }

    // Get all Offer items
    fun getAllOffers() = offerDao.getAllOffers()

    // Delete an Offer item
    suspend fun deleteOffer(offer: Offer) {
        offerDao.deleteOffer(offer)
    }
}
