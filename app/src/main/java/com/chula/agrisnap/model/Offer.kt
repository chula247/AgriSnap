package com.chula.agrisnap.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "offer")
data class Offer(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,         // Name of the offer
    val discount: Double,        // Price or discount value
    val phone: String,        // Contact number (e.g., seller or service)
    val imagePath: String     // Path to image associated with the offer
)
