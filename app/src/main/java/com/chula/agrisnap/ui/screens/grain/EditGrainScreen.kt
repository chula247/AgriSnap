package com.chula.agrisnap.ui.screens.grain

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.chula.agrisnap.navigation.ROUT_ADD_GRAIN
import com.chula.agrisnap.navigation.ROUT_GRAIN_LIST
import com.chula.agrisnap.viewmodel.GrainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditGrainScreen(
    grainId: Int?,
    navController: NavController,
    viewModel: GrainViewModel
) {
    val context = LocalContext.current
    val grainList by viewModel.allGrains.observeAsState(emptyList())
    val grain = remember(grainList) { grainList.find { it.id == grainId } }

    var name by remember { mutableStateOf(grain?.name ?: "") }
    var price by remember { mutableStateOf(grain?.price?.toString() ?: "") }
    var imagePath by remember { mutableStateOf(grain?.imagePath ?: "") }
    var showMenu by remember { mutableStateOf(false) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imagePath = it.toString()
            Toast.makeText(context, "Image Selected!", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Grain") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Home") },
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
            BottomNavigationBarGrain(navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (grain != null) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Grain Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Grain Price") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                Image(
                    painter = rememberAsyncImagePainter(model = Uri.parse(imagePath)),
                    contentDescription = "Grain Image",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(8.dp)
                )

                Button(
                    onClick = { imagePicker.launch("image/*") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    colors = ButtonDefaults.buttonColors(Color.LightGray)
                ) {
                    Text("Change Image")
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val updatedPrice = price.toDoubleOrNull()
                        if (updatedPrice != null) {
                            viewModel.updateGrain(
                                grain.copy(name = name, price = updatedPrice, imagePath = imagePath)
                            )
                            Toast.makeText(context, "Grain Updated!", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Invalid price entered!", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    Text("Update Grain")
                }
            } else {
                Text(
                    text = "Grain not found",
                    color = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { navController.popBackStack() }) {
                    Text("Go Back")
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBarGrain(navController: NavController) {
    NavigationBar(
        containerColor = Color(0xFF6F6A72),
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_GRAIN_LIST) },
            icon = { Icon(Icons.Default.Menu, contentDescription = "Grain List") },
            label = { Text("Grain") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_ADD_GRAIN) },
            icon = { Icon(Icons.Default.Menu, contentDescription = "Add Grain") },
            label = { Text("Add") }
        )
    }
}
