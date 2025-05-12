package com.chula.agrisnap.ui.screens.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chula.agrisnap.R
import com.chula.agrisnap.navigation.ROUT_ADD_DAIRY
import com.chula.agrisnap.navigation.ROUT_ADD_FRUIT
import com.chula.agrisnap.navigation.ROUT_ADD_OFFER
import com.chula.agrisnap.navigation.ROUT_ADD_PROMOTION
import com.chula.agrisnap.navigation.ROUT_ADD_VEGETABLE

@Composable
fun StateScreen(navController: NavController, userName: String = "Farmer") {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color(0xFFF1F8E9))
            .padding(16.dp)
    ) {
        // Greeting
        Text(
            text = "Welcome back, $userName 👋",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF33691E)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Promo Banner
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFDCEDC8)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(Modifier.padding(16.dp)) {
                Text("🌽 Summer Sale!", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("Add your products here", fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Categories Section
        Text("Categories", fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CategoryItem("Vegetables", R.drawable.vegetables) {
                navController.navigate(ROUT_ADD_VEGETABLE)
            }
            CategoryItem("Fruits", R.drawable.fruits) {
                navController.navigate(ROUT_ADD_FRUIT)
            }
            CategoryItem("Grains", R.drawable.grains) {
                navController.navigate(ROUT_ADD_FRUIT)
            }
            CategoryItem("Dairy", R.drawable.dairy) {
                navController.navigate(ROUT_ADD_DAIRY)
            }
            CategoryItem("Offer", R.drawable.poultry) {
                navController.navigate(ROUT_ADD_OFFER)
            }
            CategoryItem("Promotion", R.drawable.poultry) {
                navController.navigate(ROUT_ADD_PROMOTION)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Featured Products Section
        Text("Top Products", fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow {
            items(5) { index ->
                ProductCard(
                    name = "Organic Tomato",
                    price = "₹50/kg",
                    imageRes = R.drawable.mangos,
                    onClick = { /* Navigate to product */ }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Post Product Button
        Button(
            onClick = { /* navController.navigate(ROUT_POST_PRODUCT) */ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Post Your Product")
        }
    }
}

@Composable
fun CategoryItem(
    name: String,
    iconRes: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(12.dp)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = name,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 12.dp)
            )
            Text(name, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun ProductCard(name: String, price: String, imageRes: Int, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            )
            Text(name, fontWeight = FontWeight.Bold)
            Text(price, color = Color(0xFF388E3C))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStateScreen() {
    StateScreen(navController = rememberNavController())
}