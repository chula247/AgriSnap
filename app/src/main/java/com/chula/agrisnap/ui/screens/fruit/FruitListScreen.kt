package com.chula.agrisnap.ui.screens.fruit

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.chula.agrisnap.model.Fruit
import com.chula.agrisnap.navigation.ROUT_ADD_FRUIT
import com.chula.agrisnap.navigation.ROUT_FRUIT_LIST
import com.chula.agrisnap.navigation.editFruitRoute
import com.chula.agrisnap.viewmodel.FruitViewModel

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FruitListScreen(navController: NavController, viewModel: FruitViewModel) {
    val fruitList by viewModel.allFruits.observeAsState(emptyList())
    var showMenu by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    val filteredFruits = fruitList.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("Fruits", fontSize = 22.sp, fontWeight = FontWeight.SemiBold) },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFFE1F5FE)),
                    actions = {
                        IconButton(onClick = { showMenu = true }) {
                            Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                        }
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Fruit List") },
                                onClick = {
                                    navController.navigate(ROUT_FRUIT_LIST)
                                    showMenu = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Add Fruit") },
                                onClick = {
                                    navController.navigate(ROUT_ADD_FRUIT)
                                    showMenu = false
                                }
                            )
                        }
                    }
                )

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    placeholder = { Text("Search fruits...") },
                    singleLine = true,
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Gray)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF4CAF50),
                        unfocusedBorderColor = Color.Gray,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.DarkGray
                    )
                )
            }
        },
        bottomBar = { BottomNavigationBarFruit(navController) }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF1F8E9))
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(filteredFruits) { fruit ->
                FruitItem(navController, fruit, viewModel)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun FruitItem(navController: NavController, fruit: Fruit, viewModel: FruitViewModel) {
    val painter: Painter = rememberAsyncImagePainter(
        model = fruit.imagePath?.let { Uri.parse(it) } ?: Uri.EMPTY
    )
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                if (fruit.id != 0) {
                    navController.navigate(editFruitRoute(fruit.id))
                }
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box {
            Image(
                painter = painter,
                contentDescription = "Fruit Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.BottomStart)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f))
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 12.dp, bottom = 45.dp)
            ) {
                Text(
                    text = fruit.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Price: Ksh${fruit.price}",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        val smsIntent = Intent(Intent.ACTION_SENDTO)
                        smsIntent.data = "smsto:${fruit.phone}".toUri()
                        smsIntent.putExtra("sms_body", "Hello Seller, I'm interested in your fruit product.")
                        context.startActivity(smsIntent)
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                    border = BorderStroke(1.dp, Color.White)
                ) {
                    Icon(Icons.Default.Send, contentDescription = "Message", modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Message", fontSize = 12.sp)
                }

                IconButton(onClick = {
                    navController.navigate(editFruitRoute(fruit.id))
                }) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit", tint = Color.White)
                }

                IconButton(onClick = { viewModel.deleteFruit(fruit) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.White)
                }

                Icon(
                    imageVector = Icons.Default.Refresh, // Placeholder for PDF
                    contentDescription = "Download PDF",
                    tint = Color.White
                )
            }
        }
    }
}
