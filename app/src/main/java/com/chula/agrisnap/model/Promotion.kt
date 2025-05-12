package com.chula.agrisnap.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "promotion_table")
data class Promotion(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val discount: String,
    val imagePath: String
)
