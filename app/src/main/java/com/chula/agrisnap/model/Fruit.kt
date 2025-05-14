package com.chula.agrisnap.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fruits")
data class Fruit(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val price: Double,
    val phone: String,
    val imagePath: String
)
