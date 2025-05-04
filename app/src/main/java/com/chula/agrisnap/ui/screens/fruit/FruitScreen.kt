package com.chula.agrisnap.ui.screens.fruit

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.chula.agrisnap.R
import com.chula.agrisnap.ui.theme.green
import com.chula.agrisnap.navigation.ROUT_GRAIN
import androidx.compose.animation.core.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import com.chula.agrisnap.navigation.ROUT_PROFILE
import com.chula.agrisnap.navigation.ROUT_STATER

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FruitScreen(navController: NavController){
//Scaffold

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        //TopBar
        topBar = {
            TopAppBar(
                title = { Text("My Fruit List") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(ROUT_STATER)
                    }) {
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

        //BottomBar
        bottomBar = {
            NavigationBar(
                containerColor = green
            ){
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0
                        navController.navigate(ROUT_STATER)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
                    label = { Text("Favorites") },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1
                        //navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2
                          navController.navigate(ROUT_PROFILE)
                    }
                )

            }
        },

        //FloatingActionButton
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Add action */ },
                containerColor = green
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },

        //Content
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {


                //Main Contents of the page
                Text(
                    text = "Top Picks",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                val imageUrls = listOf(
                    "https://source.unsplash.com/800x600/?farm",
                    "https://source.unsplash.com/800x600/?vegetables",
                    "https://source.unsplash.com/800x600/?livestock",
                    "https://source.unsplash.com/800x600/?agriculture",
                    "https://source.unsplash.com/800x600/?harvest"
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(imageUrls) { imageUrl ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(4.dp),
                            modifier = Modifier
                                .width(250.dp)
                                .height(150.dp)
                        ) {
                            AsyncImage(
                                model = imageUrl,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                placeholder = painterResource(R.drawable.fruits),

                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column (
                    modifier = Modifier.fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {

                    //Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        listOf(
                            Triple("Apples", R.drawable.app, "ksh150.00/kg"),
                            Triple("Bananas", R.drawable.ban, "ksh130.00/kg"),
                            Triple("Oranges", R.drawable.org, "ksh100.00/kg")
                        ).forEach { (name, imageRes, price) ->
                            Card(
                                modifier = Modifier
                                    .width(110.dp)
                                    .height(200.dp)
                                    .clickable { navController.navigate(ROUT_GRAIN) },
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Image(
                                        painter = painterResource(id = imageRes),
                                        contentDescription = name,
                                        modifier = Modifier
                                            .size(70.dp)
                                            .padding(top = 4.dp)
                                    )
                                    Text(text = name, fontSize = 16.sp)
                                    Text(text = price, fontSize = 14.sp, color = Color.Gray)
                                    Button(
                                        onClick = { /* TODO: handle payment */ },
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
                    //End of Row


                    //Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        listOf(
                            Triple("Grapes", R.drawable.gra, "ksh50.00/kg"),
                            Triple("Avocados", R.drawable.avo, "ksh300.00/kg"),
                            Triple("Mangoes", R.drawable.mangos, "ksh200.20/kg")
                        ).forEach { (name, imageRes, price) ->
                            Card(
                                modifier = Modifier
                                    .width(110.dp)
                                    .height(200.dp)
                                    .clickable { navController.navigate(ROUT_GRAIN) },
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Image(
                                        painter = painterResource(id = imageRes),
                                        contentDescription = name,
                                        modifier = Modifier
                                            .size(70.dp)
                                            .padding(top = 4.dp)
                                    )
                                    Text(text = name, fontSize = 16.sp)
                                    Text(text = price, fontSize = 14.sp, color = Color.Gray)
                                    Button(
                                        onClick = { /* TODO: handle payment */ },
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
                    //End of Row


                    //Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        listOf(
                            Triple("Melons", R.drawable.mel, "ksh300.00/kg"),
                            Triple("Figs", R.drawable.fig, "ksh120.80/kg"),
                            Triple("Plums", R.drawable.plum, "ksh100.20/kg")
                        ).forEach { (name, imageRes, price) ->
                            Card(
                                modifier = Modifier
                                    .width(110.dp)
                                    .height(200.dp)
                                    .clickable { navController.navigate(ROUT_GRAIN) },
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Image(
                                        painter = painterResource(id = imageRes),
                                        contentDescription = name,
                                        modifier = Modifier
                                            .size(70.dp)
                                            .padding(top = 4.dp)
                                    )
                                    Text(text = name, fontSize = 16.sp)
                                    Text(text = price, fontSize = 14.sp, color = Color.Gray)
                                    Button(
                                        onClick = { /* TODO: handle payment */ },
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
                    //End of Row

                    //Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        listOf(
                            Triple("Peaches", R.drawable.pea, "ksh90.00/kg"),
                            Triple("Pears", R.drawable.per, "ksh350.80/kg"),
                            Triple("Lemons", R.drawable.lem, "ksh250.20/kg")
                        ).forEach { (name, imageRes, price) ->
                            Card(
                                modifier = Modifier
                                    .width(110.dp)
                                    .height(200.dp)
                                    .clickable { navController.navigate(ROUT_GRAIN) },
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Image(
                                        painter = painterResource(id = imageRes),
                                        contentDescription = name,
                                        modifier = Modifier
                                            .size(70.dp)
                                            .padding(top = 4.dp)
                                    )
                                    Text(text = name, fontSize = 16.sp)
                                    Text(text = price, fontSize = 14.sp, color = Color.Gray)
                                    Button(
                                        onClick = { /* TODO: handle payment */ },
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
                    //End of Row

                    // Inside your Column, just after the Row of grain cards
                    val infiniteTransition = rememberInfiniteTransition()
                    val animatedOffset = infiniteTransition.animateFloat(
                        initialValue = 0f,
                        targetValue = 1000f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(3000, easing = LinearEasing),
                            repeatMode = RepeatMode.Restart
                        )
                    )

                    val glowingBrush = Brush.linearGradient(
                        colors = listOf(Color.Red, Color.Yellow, Color.Green, Color.Cyan, Color.Magenta),
                        start = Offset(animatedOffset.value, 0f),
                        end = Offset(animatedOffset.value + 300f, 300f)
                    )

                    Text(
                        text = "✨ Best Value for Your Enjoyment ✨",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        style = MaterialTheme.typography.titleMedium.copy(brush = glowingBrush),
                        textAlign = TextAlign.Center
                    )


                }







            }
        }
    )

    //End of scaffold




}

@Preview(showBackground = true)
@Composable
fun FruitScreenPreview(){
    FruitScreen(navController= rememberNavController())
}