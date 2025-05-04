package com.chula.agrisnap.model

data class CartItem(
    val name: String,
    val imageRes: Int,
    val pricePerUnit: Int,
    val quantity: Int
)