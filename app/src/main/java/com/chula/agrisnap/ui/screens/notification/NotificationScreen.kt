package com.chula.agrisnaps.ui.screens.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chula.agrisnap.R
import com.chula.agrisnap.navigation.ROUT_STATER
import com.chula.agrisnap.ui.theme.green

@Composable
fun NotificationScreen(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }

    val image = painterResource(R.drawable.agr)

    val notifications = remember {
        mutableStateListOf(
            NotificationItem(1, "john123", image, "John Doe", true, "Maize", "100kg", "Nairobi", "2 hours ago", "KES 4,500"),
            NotificationItem(2, "jane456", image, "Jane Smith", true, "Beans", "50kg", "Mombasa", "1 hour ago", "KES 3,000"),
            NotificationItem(3, "ali789", image, "Ali Mwangi", false, "Wheat", "200kg", "Kisumu", "3 hours ago", "KES 6,000")
        )
    }

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = green) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedIndex == 0,
                    onClick = { navController.navigate(ROUT_STATER) }



                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
                    label = { Text("Favorites") },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2 }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Add action */ }, containerColor = green) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Box {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .padding(10.dp),
                        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                        colors = CardDefaults.cardColors(green)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row {
                                Image(
                                    painter = image,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(90.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Notification",
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                notifications.forEach { notification ->
                    TradeProfileCard(
                        item = notification,
                        onCancel = { notifications.remove(notification) },
                        onAccept = {
                            notifications.remove(notification)
                            navController.navigate("chat/${notification.receiverId}")
                        }
                    )
                }
            }
        }
    )
}

data class NotificationItem(
    val id: Int,
    val receiverId: String,
    val profileImage: Painter,
    val name: String,
    val isBuyer: Boolean,
    val productName: String,
    val quantity: String,
    val location: String,
    val timeAgo: String,
    val price: String
)

@Composable
fun TradeProfileCard(
    item: NotificationItem,
    onCancel: () -> Unit,
    onAccept: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = item.profileImage,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = item.name, fontWeight = FontWeight.Bold)
                    Text(
                        text = if (item.isBuyer) "Buyer" else "Seller",
                        color = if (item.isBuyer) Color.Green else Color.Blue,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = item.productName, fontWeight = FontWeight.Medium)
                Text(text = item.quantity, fontWeight = FontWeight.Medium)
                Text(text = item.location, fontWeight = FontWeight.Medium)
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = item.timeAgo,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(text = item.price, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onCancel,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Cancel", tint = Color.Red)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Cancel", color = Color.Red)
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = onAccept,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Check, contentDescription = "Accept", tint = Color.White)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Accept & Chat", color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationScreenPreview() {
    NotificationScreen(navController = rememberNavController())
}
