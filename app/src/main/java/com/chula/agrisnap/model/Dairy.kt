package com.chula.agrisnap.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dairy")
data class Dairy(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val price: Double,
    val phone: String,
    val imagePath: String
)