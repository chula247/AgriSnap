package com.chula.agrisnap.ui.screens.starter

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chula.agrisnap.navigation.ROUT_DAIRY
import com.chula.agrisnap.navigation.ROUT_FRUIT
import com.chula.agrisnap.navigation.ROUT_GRAIN
import com.chula.agrisnap.navigation.ROUT_NOTIFICATION
import com.chula.agrisnap.navigation.ROUT_POULTRY
import com.chula.agrisnap.navigation.ROUT_PROFILE
import com.chula.agrisnap.navigation.ROUT_VEGETABLE
import com.chula.agrisnap.ui.theme.green
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.ui.geometry.Offset
import coil.compose.rememberImagePainter
import com.chula.agrisnap.R // Replace with your package name
import com.chula.agrisnap.navigation.ROUTE_OFFER
import com.chula.agrisnap.navigation.ROUTE_PROMOTION
import com.chula.agrisnap.navigation.ROUT_CART
import com.chula.agrisnap.navigation.ROUT_CHATS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StaterScreen(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }

    // Rainbow glow animation setup
    val infiniteTransition = rememberInfiniteTransition()
    val animatedHue by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    val glowColor = Color.hsl(animatedHue, 1f, 0.5f)

    Scaffold(
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
        topBar = {
            TopAppBar(
                title = { Text("Much Welcome") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = {
                        navController.navigate(ROUT_NOTIFICATION)
                    }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications")
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
                    onClick = { selectedIndex = 0 }
                )

                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedIndex == 2,
                    onClick = { navController.navigate(ROUT_PROFILE) }
                )
            }
        },

        content = { paddingValues ->
            val scrollState = rememberScrollState()
            val coroutineScope = rememberCoroutineScope()

            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                Column(modifier = Modifier.padding(16.dp)) {
                    Spacer(modifier = Modifier.height(20.dp))

                    // Offers Section
                    val offerList = listOf(
                        Triple("https://via.placeholder.com/300x150.png?text=Offer+1", "50% Off on Groceries", "Grab Now"),
                        Triple("https://via.placeholder.com/300x150.png?text=Offer+2", "Free Delivery Above ₹500", "Shop Now"),
                        Triple("https://via.placeholder.com/300x150.png?text=Offer+3", "New User Special", "Claim Offer")
                    )
                    val offerScrollState = rememberLazyListState()

                    LaunchedEffect(Unit) {
                        while (true) {
                            delay(3000)
                            val nextIndex = (offerScrollState.firstVisibleItemIndex + 1) % offerList.size
                            coroutineScope.launch {
                                offerScrollState.animateScrollToItem(nextIndex)
                            }
                        }
                    }

                    Text("Offers", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                    Spacer(modifier = Modifier.height(10.dp))

                    LazyRow(
                        state = offerScrollState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(offerList) { (imageUrl, title, buttonText) ->
                            Card(
                                shape = RoundedCornerShape(12.dp),
                                elevation = CardDefaults.elevatedCardElevation(4.dp),
                                modifier = Modifier
                                    .width(300.dp)
                                    .shadow(
                                        elevation = 15.dp,
                                        shape = RoundedCornerShape(12.dp),
                                        ambientColor = glowColor.copy(alpha = 0.8f),
                                        spotColor = glowColor.copy(alpha = 0.8f)
                                    )
                            ) {
                                val title = "Special Discount"
                                val buttonText = "Shop Now"

                                Column {
                                    Image(
                                        painter = painterResource(id = R.drawable.fruits),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(100.dp)
                                    )
                                    Text(
                                        title,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    Button(
                                        onClick = { navController.navigate(ROUTE_OFFER) },
                                        modifier = Modifier.padding(horizontal = 8.dp)
                                    ) {
                                        Text(buttonText)
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                }

                // Category Section
                var showAllCategories by remember { mutableStateOf(false) }
                val categoryList = listOf("Vegetables", "Fruits", "Grains", "Dairy", "Poultry")
                val categoryImages = mapOf(
                    "Vegetables" to "https://source.unsplash.com/120x60/?vegetables",
                    "Fruits" to "https://source.unsplash.com/120x60/?fruits",
                    "Grains" to "https://source.unsplash.com/120x60/?grains",
                    "Dairy" to "https://source.unsplash.com/120x60/?dairy",
                )
                val visibleCategories = if (showAllCategories) categoryList else categoryList.take(5)
                val categoryScrollState = rememberLazyListState()

                LaunchedEffect(!showAllCategories) {
                    while (!showAllCategories) {
                        delay(2500)
                        val nextIndex = (categoryScrollState.firstVisibleItemIndex + 1) % visibleCategories.size
                        coroutineScope.launch {
                            categoryScrollState.animateScrollToItem(nextIndex)
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Categories", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                    Text(
                        text = if (showAllCategories) "Show Less" else "View All",
                        color = Color.Blue,
                        modifier = Modifier
                            .clickable { showAllCategories = !showAllCategories }
                            .padding(end = 8.dp)
                    )
                }

                LazyRow(
                    state = categoryScrollState,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    items(visibleCategories) { category ->
                        val imageUrl = categoryImages[category]
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.elevatedCardElevation(4.dp),
                            modifier = Modifier
                                .width(120.dp)
                                .fillMaxHeight()
                                .clickable {
                                    when (category) {
                                        "Vegetables" -> navController.navigate(ROUT_VEGETABLE)
                                        "Fruits" -> navController.navigate(ROUT_FRUIT)
                                        "Grains" -> navController.navigate(ROUT_GRAIN)
                                        "Dairy" -> navController.navigate(ROUT_DAIRY)
                                    }
                                }
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color(0xFFE0F7FA))
                                    .padding(8.dp)
                            ) {
                                imageUrl?.let {
                                    Box(
                                        modifier = Modifier
                                            .height(60.dp)
                                            .fillMaxWidth()
                                            .padding(2.dp)
                                            .shadow(
                                                elevation = 15.dp,
                                                shape = RoundedCornerShape(8.dp),
                                                ambientColor = glowColor.copy(alpha = 0.7f),
                                                spotColor = glowColor.copy(alpha = 0.7f)
                                            )
                                    ) {
                                        val categoryImages = mapOf(
                                            "Vegetables" to R.drawable.vegetables,
                                            "Fruits" to R.drawable.fruits,
                                            "Grains" to R.drawable.grains,
                                            "Dairy" to R.drawable.dairy,
                                        )

                                        val imageRes = categoryImages[category] ?: R.drawable.placeholder

                                        Image(
                                            painter = painterResource(id = imageRes),
                                            contentDescription = "$category Image",
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .clip(RoundedCornerShape(8.dp)),
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    category,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }

                // Promotions Section
                val imageList = listOf(
                    "https://via.placeholder.com/400x200.png?text=Banner+1",
                    "https://via.placeholder.com/400x200.png?text=Banner+2",
                    "https://via.placeholder.com/400x200.png?text=Banner+3"
                )

                // Scrolling banners
                val bannerScrollState = rememberLazyListState()
                val bannerWidth = remember { 400.dp }

                LaunchedEffect(bannerScrollState) {
                    while (true) {
                        delay(3000)  // Change every 3 seconds
                        val nextIndex = (bannerScrollState.firstVisibleItemIndex + 1) % imageList.size
                        coroutineScope.launch {
                            bannerScrollState.animateScrollToItem(nextIndex)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text("Promotions", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                Spacer(modifier = Modifier.height(10.dp))

                LazyRow(
                    state = bannerScrollState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(imageList) { imageUrl ->
                        Image(
                            painter = rememberImagePainter(imageUrl),
                            contentDescription = null,
                            modifier = Modifier
                                .width(bannerWidth)
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(12.dp))
                        )
                    }
                }
            }
        }
    )
}
