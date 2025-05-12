package com.chula.agrisnap.viewmodel


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.chula.agrisnap.R
import com.chula.agrisnap.model.ChatMessage

class ChatViewModel : ViewModel() {

    private val _messages = mutableStateListOf(
        ChatMessage("Alice", "How's your poultry today?", R.drawable.profile1),
        ChatMessage("You", "Very fresh and healthy!", R.drawable.profile2)
    )
    val messages: List<ChatMessage> = _messages

    fun sendMessage(text: String) {
        if (text.isNotBlank()) {
            _messages.add(ChatMessage("You", text, R.drawable.profile2))
        }
    }
}
