package com.chula.agrisnap.model

data class ChatMessage(
    val sender: String,
    val text: String,
    val profileImage: Int, // Image resource ID
)


