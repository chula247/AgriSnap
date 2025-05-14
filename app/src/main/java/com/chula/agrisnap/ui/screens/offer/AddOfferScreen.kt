package com.chula.agrisnap.ui.screens.offer

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.chula.agrisnap.R
import com.chula.agrisnap.navigation.ROUT_ADD_OFFER
import com.chula.agrisnap.navigation.ROUT_OFFER_LIST
import com.chula.agrisnap.ui.screens.vegetable.BottomNavigationBar
import com.chula.agrisnap.viewmodel.OfferViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddOfferScreen(navController: NavController, viewModel: OfferViewModel) {
    var title by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var showMenu by remember { mutableStateOf(false) }

    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let { imageUri = it }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Offer") },
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
                            text = { Text("Offer List") },
                            onClick = {
                                navController.navigate(ROUT_OFFER_LIST)
                                showMenu = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Add Offer") },
                            onClick = {
                                navController.navigate(ROUT_ADD_OFFER)
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
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Offer Title") },
                    leadingIcon = { Icon(painter = painterResource(R.drawable.name), contentDescription = "Title") },
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
                        if (title.isNotBlank() && imageUri != null) {
                            viewModel.addOffer(title, 0.0, "", imageUri.toString())
                            navController.navigate(ROUT_OFFER_LIST)
                        } else {
                            // You can show a Toast or Snackbar here to inform the user
                            println("Invalid input: make sure all fields are filled correctly.")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(Color.LightGray)
                ) {
                    Text("Add Offer", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    )
}
