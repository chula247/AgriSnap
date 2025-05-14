package com.chula.agrisnap.ui.screens.offer

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
import com.chula.agrisnap.navigation.ROUT_ADD_OFFER
import com.chula.agrisnap.navigation.ROUT_OFFER_LIST
import com.chula.agrisnap.viewmodel.OfferViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditOfferScreen(offerId: Int?, navController: NavController, viewModel: OfferViewModel) {
    val context = LocalContext.current
    val offerList by viewModel.allOffers.observeAsState(emptyList())
    val offer = remember(offerList) { offerList.find { it.id == offerId } }

    var title by remember { mutableStateOf(offer?.name ?: "") }
    var phone by remember { mutableStateOf(offer?.phone ?: "") }
    var discount by remember { mutableStateOf(offer?.discount?.toString() ?: "") }
    var imagePath by remember { mutableStateOf(offer?.imagePath ?: "") }
    var showMenu by remember { mutableStateOf(false) }

    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imagePath = it.toString()
            Toast.makeText(context, "Image Selected!", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Offer") },
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
                            text = { Text("All Offers") },
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
        bottomBar = { BottomNavigationBarOffer(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (offer != null) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Offer Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = discount,
                    onValueChange = { discount = it },
                    label = { Text("Discount %") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                Image(
                    painter = rememberAsyncImagePainter(model = Uri.parse(imagePath)),
                    contentDescription = "Offer Image",
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
                        val discountValue = discount.toDoubleOrNull()
                        if (discountValue != null) {
                            viewModel.updateOffer(
                                offer.copy(
                                    name = title,
                                    phone = phone,
                                    discount = discountValue,
                                    imagePath = imagePath
                                )
                            )
                            Toast.makeText(context, "Offer Updated!", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Invalid discount entered!", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    Text("Update Offer")
                }
            } else {
                Text(text = "Offer not found", color = MaterialTheme.colorScheme.error)
                Button(onClick = { navController.popBackStack() }) {
                    Text("Go Back")
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBarOffer(navController: NavController) {
    NavigationBar(
        containerColor = Color(0xFF6F6A72),
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_OFFER_LIST) },
            icon = { Icon(Icons.Default.Menu, contentDescription = "Offer List") },
            label = { Text("Offers") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_ADD_OFFER) },
            icon = { Icon(Icons.Default.Menu, contentDescription = "Add Offer") },
            label = { Text("Add") }
        )
    }
}
