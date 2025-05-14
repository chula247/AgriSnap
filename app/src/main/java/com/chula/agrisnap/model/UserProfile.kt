package com.chula.agrisnap.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val username: String,
    val displayName: String,
    val bio: String,
    val profileImageUri: String?,
    val followers: Int,
    val following: Int
)
