package com.chula.agrisnap.ui.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chula.agrisnap.R
import com.chula.agrisnap.model.CartItem
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chula.agrisnap.navigation.ROUT_CHECK
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.chula.agrisnap.navigation.ROUT_STATER


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(cartItems: List<CartItem>, navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(vertical = 16.dp)) {

        // TopAppBar with glowing back arrow
        TopAppBar(
            title = {

                Text(
                    text = "Back",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp,
                        shadow = Shadow(
                            color = Color.Yellow,
                            offset = Offset(2f, 2f), // this must be from ui.unit
                            blurRadius = 8f
                        )
                    )
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack() // Navigate back to previous screen
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Green
                    )
                }
            }
        )
        LazyColumn(modifier = Modifier
            .weight(1f)
            .padding(top = 8.dp, bottom = 15.dp)
        ) {
            items(cartItems) { item ->
                CartItemCard(item = item)
            }
        }

        Divider()

        val subtotal = cartItems.sumOf { it.pricePerUnit * it.quantity }
        val deliveryFee = if (subtotal > 1000) 0 else 50
        val total = subtotal + deliveryFee

        Column(modifier = Modifier.padding(16.dp)) {
            SummaryRow("Subtotal", subtotal)
            SummaryRow("Delivery", deliveryFee)
            SummaryRow("Total", total, true)

            Spacer(modifier = Modifier.height(16.dp))

            // Button with navigation
            Button(
                onClick = {
                    navController.navigate(ROUT_CHECK) // Use your checkout screen route here
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Proceed to Checkout")
            }
        }
    }
}

@Composable
fun CartItemCard(item: CartItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = item.name, style = MaterialTheme.typography.titleMedium)
                Text("Ksh ${item.pricePerUnit} x ${item.quantity} = Ksh ${item.pricePerUnit * item.quantity}")
            }
        }
    }
}

@Composable
fun SummaryRow(label: String, amount: Int, bold: Boolean = false) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal)
        Text("Ksh $amount", fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal)
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewCartScreen() {
    val sampleItems = listOf(
        CartItem("Banana", R.drawable.ban, 100, 2),
        CartItem("Burger", R.drawable.bar, 250, 1)
    )
    val navController = rememberNavController()
    CartScreen(cartItems = sampleItems, navController = navController)
}