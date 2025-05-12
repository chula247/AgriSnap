package com.chula.agrisnap.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_messages")
data class ChatMessage(
    val sender: String,
    val text: String,
    val profileImage: Int,
    val timestamp: Long = System.currentTimeMillis(), // Optional: Add a timestamp if you want
    @PrimaryKey(autoGenerate = true) val id: Long = 0 // Add a primary key
)


