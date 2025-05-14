package com.chula.agrisnap.ui.screens.fruit

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.chula.agrisnap.navigation.ROUT_ADD_FRUIT
import com.chula.agrisnap.navigation.ROUT_FRUIT_LIST
import com.chula.agrisnap.viewmodel.FruitViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFruitScreen(navController: NavController, viewModel: FruitViewModel) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var showMenu by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imageUri = it
            Log.d("ImagePicker", "Selected image URI: $it")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Fruit", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(Color.LightGray),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu")
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
                    label = { Text("Fruit Name") },
                    leadingIcon = { Icon(painter = painterResource(R.drawable.name), contentDescription = "Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Fruit Price") },
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
                            contentDescription = "Selected Image",
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
                        if (priceValue != null) {
                            imageUri?.toString()?.let { viewModel.addFruit(name, priceValue, phone, it) }
                            navController.navigate(ROUT_FRUIT_LIST)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(Color.LightGray)
                ) {
                    Text("Add Fruit", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    )
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        containerColor = Color(0xFF6F6A72),
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_FRUIT_LIST) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Fruit List") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_ADD_FRUIT) },
            icon = { Icon(Icons.Default.AddCircle, contentDescription = "Add Fruit") },
            label = { Text("Add") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_ADD_FRUIT) },
            icon = { Icon(painter = painterResource(R.drawable.profile), contentDescription = "") },
            label = { Text("Profile") }
        )
    }
}
