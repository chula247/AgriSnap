package com.chula.agrisnap.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey val id: Int,
    val name: String,
    val email: String,
    val bio: String? = null,
    val isProfileCompleted: Boolean = false
)