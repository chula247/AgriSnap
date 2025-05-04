package com.chula.agrisnap.ui.screens.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextButton
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun CheckoutScreen(navController: NavController) {
    var selectedPayment by remember { mutableStateOf("Mobile Money") }
    var notes by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Checkout", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        // Product Summary
        Text("Order Summary", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        ProductItem(name = "Tomatoes", quantity = "5kg", price = 100.0)
        ProductItem(name = "Fresh Eggs", quantity = "1 dozen", price = 30.0)



        Spacer(modifier = Modifier.height(16.dp))

        // Delivery Info
        Text("Delivery Information", style = MaterialTheme.typography.titleMedium)
        Text("John Doe")
        Text("123 Farm Lane, Rural Area")
        Text("Phone: 0912-345678")
        TextButton(onClick = { /* Handle edit */ }) {
            Text("Edit")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Payment Method
        Text("Payment Method", style = MaterialTheme.typography.titleMedium)
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedPayment == "Mobile Money",
                onClick = { selectedPayment = "Mobile Money" }
            )
            Text("Mobile Money")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedPayment == "Cash on Delivery",
                onClick = { selectedPayment = "Cash on Delivery" }
            )
            Text("Cash on Delivery")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Notes
        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Add a note (optional)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Order Total
        Text("Subtotal: Ksh 130")
        Text("Delivery: Ksh 20")
        Text("Total: Ksh 150", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /* Handle order confirmation */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Confirm Order – Ksh 150")
        }
    }
}


@Composable
fun ProductItem(name: String, quantity: String, price: Double) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$name ($quantity)")
        Text(text = "Ksh ${price.toInt()}")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CheckoutScreenPreview() {
    CheckoutScreen(navController = rememberNavController())
}