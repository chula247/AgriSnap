package com.chula.agrisnap.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class Profile(
    @PrimaryKey val id: Int = 0,
    val name: String,
    val username: String,
    val bio: String,
    val profileImageUri: String?,  // Use String to store Uri.toString()
    val followers: Int,
    val following: Int
)