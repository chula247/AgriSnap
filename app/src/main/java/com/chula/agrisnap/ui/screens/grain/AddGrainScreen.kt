package com.chula.agrisnap.ui.screens.grain

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.chula.agrisnap.R
import com.chula.agrisnap.navigation.ROUT_ADD_GRAIN
import com.chula.agrisnap.navigation.ROUT_GRAIN_LIST
import com.chula.agrisnap.ui.screens.vegetable.BottomNavigationBar
import com.chula.agrisnap.viewmodel.GrainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGrainScreen(navController: NavController, viewModel: GrainViewModel) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var showMenu by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let { imageUri = it }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Grain") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(Color.LightGray),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(
                            text = { Text("Grain List") },
                            onClick = {
                                navController.navigate(ROUT_GRAIN_LIST)
                                showMenu = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Add Grain") },
                            onClick = {
                                navController.navigate(ROUT_ADD_GRAIN)
                                showMenu = false
                            }
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(navController)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Grain Name") },
                    leadingIcon = { Icon(painter = painterResource(R.drawable.name), contentDescription = "Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Grain Price") },
                    leadingIcon = { Icon(painter = painterResource(R.drawable.price), contentDescription = "Price") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone Number") },
                    leadingIcon = { Icon(painter = painterResource(R.drawable.phone), contentDescription = "Phone") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
                        .clickable { imagePicker.launch("image/*") },
                    contentAlignment = Alignment.Center
                ) {
                    if (imageUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(model = imageUri),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painter = painterResource(R.drawable.image), contentDescription = "Pick Image")
                            Text("Tap to pick image", color = Color.DarkGray)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        val priceValue = price.toDoubleOrNull()
                        if (priceValue != null && imageUri != null) {
                            viewModel.addGrain(name, priceValue, phone, imageUri.toString())
                            navController.navigate(ROUT_GRAIN_LIST)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(Color.LightGray)
                ) {
                    Text("Add Grain", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    )
}
