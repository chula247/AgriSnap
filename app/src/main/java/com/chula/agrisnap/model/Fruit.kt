package com.chula.agrisnap.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fruit") // Changed table name to "fruit"
data class Fruit(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Auto-generate primary key
    val name: String, // Name of the fruit
    val price: Double, // Price of the fruit
    val phone: String, // Phone number of the seller (optional, can be modified as per use case)
    val imagePath: String // Path to the image of the fruit
)
