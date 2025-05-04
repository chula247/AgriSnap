package com.chula.agrisnaps.ui.screens.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chula.agrisnap.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Model for a message
data class ChatMessage(
    val sender: String, // "me" or receiverId
    val content: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(receiverId: String, navController: NavController) {
    var message by remember { mutableStateOf("") }
    var replyTo by remember { mutableStateOf<ChatMessage?>(null) }
    val messages = remember { mutableStateListOf<ChatMessage>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chat with $receiverId") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                for ((index, msg) in messages.withIndex()) {
                    val isMe = msg.sender == "me"
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                            .background(if (isMe) Color(0xFFD0F0C0) else Color(0xFFEDEDED))
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "${if (isMe) "You" else receiverId}: ${msg.content}",
                            fontSize = 16.sp
                        )
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Reply",
                                color = Color.Blue,
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .clickable { replyTo = msg }
                                    .padding(end = 8.dp)
                            )
                            Text(
                                text = "Delete",
                                color = Color.Red,
                                fontSize = 12.sp,
                                modifier = Modifier.clickable {
                                    messages.removeAt(index)
                                    if (replyTo == msg) replyTo = null
                                }
                            )
                        }
                    }
                }
            }

            if (replyTo != null) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .background(Color.LightGray)
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Replying to: ${replyTo?.content}",
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Cancel",
                        color = Color.Red,
                        modifier = Modifier.clickable { replyTo = null }
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = message,
                    onValueChange = { message = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Type a message") }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    if (message.isNotBlank()) {
                        val finalMessage = if (replyTo != null) {
                            ChatMessage("me", "Reply to [${replyTo!!.content}]: $message")
                        } else {
                            ChatMessage("me", message)
                        }
                        messages.add(finalMessage)
                        message = ""
                        replyTo = null
                    }
                }) {
                    Text("Send")
                }
            }
        }
    }
}

