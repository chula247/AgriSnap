package com.chula.agrisnap.ui.screens.chats

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.chula.agrisnap.viewmodel.ChatViewModel

@Composable
fun ChatsScreen(navController: NavController) {
    val viewModel: ChatViewModel = viewModel(
        modelClass = ChatViewModel::class.java,
        key = "chatViewModel"
    )
    val messages = viewModel.messages
    var newMessage by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        // Top Bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color(0xFF4CAF50))
            }
            Text(
                "Chat Now",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    shadow = Shadow(Color.Magenta, Offset(2f, 2f), 4f)
                ),
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        // Message list
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            items(messages) { message ->
                val isSender = message.sender == "You"
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .padding(start = if (isSender) 64.dp else 8.dp, end = if (isSender) 8.dp else 64.dp)
                ) {
                    if (!isSender) {
                        Image(
                            painter = painterResource(id = message.profileImage),
                            contentDescription = "Profile",
                            modifier = Modifier.size(40.dp).clip(CircleShape)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        color = if (isSender) Color(0xFFB2FF59) else Color(0xFFE0F7FA),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "${message.sender}: ${message.text}",
                            modifier = Modifier.padding(12.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    if (isSender) {
                        Image(
                            painter = painterResource(id = message.profileImage),
                            contentDescription = "Profile",
                            modifier = Modifier.size(40.dp).clip(CircleShape)
                        )
                    }
                }
            }
        }

        // Input section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = newMessage,
                onValueChange = { newMessage = it },
                label = { Text("Type a message") },
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color.Gray, RoundedCornerShape(50))
                    .background(Color.Transparent, RoundedCornerShape(50)),
                shape = RoundedCornerShape(50),
                singleLine = true
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    viewModel.sendMessage(newMessage)
                    newMessage = ""
                },
                shape = RoundedCornerShape(50)
            ) {
                Text("Send")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ChatsScreenPreview() {
    val navController = rememberNavController()

    // Optional: Use a ViewModelProvider if needed for advanced mocking.
    ChatsScreen(navController = navController)
}