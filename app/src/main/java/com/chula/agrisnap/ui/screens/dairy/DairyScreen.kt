package com.chula.agrisnap.ui.screens.dairy

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.chula.agrisnap.navigation.ROUT_DAIRY
import com.chula.agrisnap.navigation.ROUT_PROFILE
import com.chula.agrisnap.navigation.ROUT_STATER
import com.chula.agrisnap.ui.theme.green
import com.chula.agrisnap.viewmodel.DairyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DairyScreen(navController: NavController, dairyViewModel: DairyViewModel = viewModel()) {
    var selectedIndex by remember { mutableStateOf(0) }

    val dairyItems = dairyViewModel.allDairies.observeAsState(emptyList()).value
    val mContext = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Dairy List") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back/nav */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = green,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },

        bottomBar = {
            NavigationBar(containerColor = green) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedIndex == 0,
                    onClick = {
                        selectedIndex = 0
                        navController.navigate(ROUT_STATER)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
                    label = { Text("Favorites") },
                    selected = selectedIndex == 1,
                    onClick = { /* Handle favorites click */ }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedIndex == 2,
                    onClick = {
                        selectedIndex = 2
                        navController.navigate(ROUT_PROFILE)
                    }
                )
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Add action */ },
                containerColor = green
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },

        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                Text(
                    text = "Top Picks",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(dairyItems) { dairy ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(4.dp),
                            modifier = Modifier
                                .width(250.dp)
                                .height(150.dp)
                        ) {
                            AsyncImage(
                                model = dairy.imagePath,
                                contentDescription = dairy.name,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    dairyItems.chunked(3).forEach { dairyRow ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            dairyRow.forEach { dairy ->
                                Card(
                                    modifier = Modifier
                                        .width(110.dp)
                                        .height(200.dp)
                                        .clickable { navController.navigate(ROUT_DAIRY) },
                                    elevation = CardDefaults.cardElevation(4.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(8.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        AsyncImage(
                                            model = dairy.imagePath,
                                            contentDescription = dairy.name,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(70.dp)
                                                .padding(top = 4.dp)
                                        )
                                        Text(text = dairy.name, fontSize = 16.sp)
                                        Text(text = dairy.price.toString(), fontSize = 14.sp, color = Color.Gray)
                                        Button(
                                            onClick = {
                                                val simToolKitLaunchIntent =
                                                    mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                                                simToolKitLaunchIntent?.let { mContext.startActivity(it) }
                                            },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = 4.dp)
                                        ) {
                                            Text("Pay")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DairyScreenPreview() {
    DairyScreen(navController = rememberNavController())
}
