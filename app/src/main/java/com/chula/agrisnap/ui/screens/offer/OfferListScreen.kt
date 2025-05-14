package com.chula.agrisnap.ui.screens.offer

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
import com.chula.agrisnap.model.Offer
import com.chula.agrisnap.navigation.ROUT_ADD_OFFER
import com.chula.agrisnap.navigation.ROUT_OFFER_LIST
import com.chula.agrisnap.navigation.editOfferRoute
import com.chula.agrisnap.viewmodel.OfferViewModel

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfferListScreen(navController: NavController, viewModel: OfferViewModel) {
    val offerList by viewModel.allOffers.observeAsState(emptyList())
    var showMenu by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    val filteredOffers = offerList.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("Offers", fontSize = 22.sp, fontWeight = FontWeight.SemiBold) },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFFFFF9C4)),
                    actions = {
                        IconButton(onClick = { showMenu = true }) {
                            Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                        }
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
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

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    placeholder = { Text("Search offers...") },
                    singleLine = true,
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Gray)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFFA000),
                        unfocusedBorderColor = Color.Gray,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.DarkGray
                    )
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFDE7))
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(filteredOffers) { offer ->
                OfferItem(navController, offer, viewModel)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun OfferItem(navController: NavController, offer: Offer, viewModel: OfferViewModel) {
    val painter: Painter = rememberAsyncImagePainter(
        model = offer.imagePath?.let { Uri.parse(it) } ?: Uri.EMPTY
    )
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                if (offer.id != 0) {
                    navController.navigate(editOfferRoute(offer.id))
                }
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box {
            Image(
                painter = painter,
                contentDescription = "Offer Image",
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
                    text = offer.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Price: Ksh${offer.discount}",
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
                        smsIntent.data = "smsto:${offer.phone}".toUri()
                        smsIntent.putExtra("sms_body", "Hello Seller, I'm interested in your offer.")
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
                    navController.navigate(editOfferRoute(offer.id))
                }) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit", tint = Color.White)
                }

                IconButton(onClick = { viewModel.deleteOffer(offer) }) {
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
