package com.chula.agrisnap.ui.screens.grain

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chula.agrisnap.R
import com.chula.agrisnap.navigation.ROUT_HOME
import com.chula.agrisnap.ui.theme.green
import com.chula.agrisnap.ui.theme.neworange



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(navController: NavController){


    //Scaffold

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(


        //Content
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {


                //Main Contents of the page
                Column (modifier = Modifier.fillMaxSize()

                ) {

                    //Box
                    Box() {
                        //Card
                        Card(
                            modifier = Modifier.fillMaxWidth().height(200.dp).padding(10.dp),
                            shape = RoundedCornerShape(topStart = 60.dp, topEnd  = 60.dp),
                            colors = CardDefaults.cardColors(neworange)
                        ) {


                            Spacer(modifier = Modifier.height(10.dp))


                            Column(
                                modifier = Modifier
                                    .fillMaxSize()

                                    .padding(16.dp), // Optional padding
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "New Post",
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )



                                Spacer(modifier = Modifier.height(10.dp))


                                Spacer(modifier = Modifier.height(10.dp))



                                Spacer(modifier = Modifier.height(10.dp))




                            }


                        }
                        //End of Card

                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(150.dp)
                                .align(alignment = Alignment.BottomCenter)
                                .padding(start = 20.dp, end = 20.dp, bottom = 90.dp)
                                .offset(y = 70.dp)


                        ) {
                            //Contents of Card




                            Spacer(modifier = Modifier.width(10.dp))
                            Row () {

                                Column(
                                    modifier = Modifier.padding(start = 20.dp, top = 20.dp).verticalScroll(rememberScrollState())
                                ) {
                                    Text(
                                        text = "I want to:",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.ExtraBold

                                    )
                                    Spacer(modifier = Modifier.height(10.dp))

                           //Selection part
                                    var selectedOption by remember { mutableStateOf("Buy") }

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        RadioButton(
                                            selected = selectedOption == "Buy",
                                            onClick = { selectedOption = "Buy" }
                                        )
                                        Text(
                                            text = "Buy",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.ExtraBold
                                        )

                                        Spacer(modifier = Modifier.width(60.dp))

                                        RadioButton(
                                            selected = selectedOption == "Sell",
                                            onClick = { selectedOption = "Sell" }
                                        )
                                        Text(
                                            text = "Sell",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.ExtraBold
                                        )
                                    }

                              //End of selecton part
                                    Spacer(modifier = Modifier.height(30.dp))

                                    var expanded by remember { mutableStateOf(false) }
                                    var selectedOptionText by remember { mutableStateOf("") }

                                    val options = listOf("Teff", "Wheat", "Oats", "Rice", "Corn","Barley","Sorghum","Rye","Millet","Tritical","Amaranth","Buckwheat","Quinoa","Spelt","Fonio") // Your options here

                                    Column {
                                        Text(
                                            text = "Pick type",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold
                                        )

                                        Spacer(modifier = Modifier.height(10.dp))

                                        ExposedDropdownMenuBox(
                                            expanded = expanded,
                                            onExpandedChange = { expanded = !expanded }
                                        ) {
                                            TextField(
                                                value = selectedOptionText,
                                                onValueChange = {},
                                                readOnly = true,
                                                label = { Text("Select type") },
                                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                                                modifier = Modifier.menuAnchor()
                                            )

                                            ExposedDropdownMenu(
                                                expanded = expanded,
                                                onDismissRequest = { expanded = false }
                                            ) {
                                                options.forEach { selectionOption ->
                                                    DropdownMenuItem(
                                                        text = { Text(selectionOption) },
                                                        onClick = {
                                                            selectedOptionText = selectionOption
                                                            expanded = false
                                                        }
                                                    )
                                                }
                                            }
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(10.dp))

                                    var isEditing by remember { mutableStateOf(false) }
                                    var address by remember { mutableStateOf(
                                        "No.30, Raju colony,\nRayachoty-Galiveedu Rd,\nKothapeta, Rayachoty,\nAndhra Pradesh-516264"
                                    ) }

                                    Column {
                                        Text(
                                            text = "Address",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.ExtraBold
                                        )

                                        Spacer(modifier = Modifier.height(10.dp))

                                        if (isEditing) {
                                            TextField(
                                                value = address,
                                                onValueChange = { address = it },
                                                label = { Text("Edit Address") },
                                                modifier = Modifier.fillMaxWidth()
                                            )

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Button(onClick = { isEditing = false }) {
                                                Text("Save")
                                            }
                                        } else {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                verticalAlignment = Alignment.Top,
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Text(
                                                    text = address,
                                                    fontSize = 20.sp,
                                                    fontWeight = FontWeight.ExtraBold,
                                                    modifier = Modifier.weight(1f) // This will allow text to take space
                                                )

                                                IconButton(
                                                    onClick = { isEditing = true }
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.AddCircle,
                                                        contentDescription = "Edit Address",
                                                        tint = Color.Blue
                                                    )
                                                }
                                            }

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Divider( // this draws the line
                                                color = Color.Gray,
                                                thickness = 1.dp
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(10.dp))

                                    var isEditings by remember { mutableStateOf(false) }
                                    var unit by remember { mutableStateOf("30") } // store it as a string for easier editing

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        if (isEditing) {
                                            TextField(
                                                value = unit,
                                                onValueChange = { unit = it },
                                                label = { Text("Unit") },
                                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                                singleLine = true,
                                                modifier = Modifier.weight(1f)
                                            )
                                        } else {
                                            Box(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .clickable { isEditing = true }
                                                    .padding(8.dp)
                                            ) {
                                                Text(
                                                    text = "Unit = $unit",
                                                    fontSize = 20.sp,
                                                    fontWeight = FontWeight.ExtraBold
                                                )
                                            }
                                        }

                                        Spacer(modifier = Modifier.width(8.dp)) // small space

                                        // Short vertical line (divider)
                                        Box(
                                            modifier = Modifier
                                                .height(30.dp) // short vertical height
                                                .width(1.dp)
                                                .background(Color.Gray)
                                        )

                                        Spacer(modifier = Modifier.width(8.dp)) // small space

                                        // Text for "kg"
                                        var isEditing by remember { mutableStateOf(false) }
                                        var unit by remember { mutableStateOf("30") }

                                        var expanded by remember { mutableStateOf(false) } // for dropdown
                                        var selectedUnit by remember { mutableStateOf("kg") } // selected unit
                                        val unitOptions = listOf("kg", "g", "lb", "oz") // units list

                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                        ) {
                                            if (isEditing) {
                                                TextField(
                                                    value = unit,
                                                    onValueChange = { unit = it },
                                                    label = { Text("Unit") },
                                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                                    singleLine = true,
                                                    modifier = Modifier.weight(1f)
                                                )
                                            } else {
                                                Box(
                                                    modifier = Modifier
                                                        .weight(1f)
                                                        .clickable { isEditing = true }
                                                        .padding(8.dp)
                                                ) {
                                                    Text(
                                                        text = "Unit = $unit",
                                                        fontSize = 20.sp,
                                                        fontWeight = FontWeight.ExtraBold
                                                    )
                                                }
                                            }

                                            Spacer(modifier = Modifier.width(8.dp))

                                            // Vertical separator line
                                            Box(
                                                modifier = Modifier
                                                    .height(30.dp)
                                                    .width(1.dp)
                                                    .background(Color.Gray)
                                            )

                                            Spacer(modifier = Modifier.width(8.dp))

                                            // Dropdown for selecting unit
                                            Box {
                                                Text(
                                                    text = selectedUnit,
                                                    fontSize = 20.sp,
                                                    fontWeight = FontWeight.ExtraBold,
                                                    modifier = Modifier
                                                        .clickable { expanded = true }
                                                        .padding(8.dp)
                                                )

                                                DropdownMenu(
                                                    expanded = expanded,
                                                    onDismissRequest = { expanded = false }
                                                ) {
                                                    unitOptions.forEach { unitOption ->
                                                        DropdownMenuItem(
                                                            text = { Text(unitOption) },
                                                            onClick = {
                                                                selectedUnit = unitOption
                                                                expanded = false
                                                            }
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }



                                    var expandedQuantity by remember { mutableStateOf(false) }
                                    var quantity by remember { mutableStateOf("1") } // Default quantity
                                    val quantityOptions = listOf("1", "2", "5", "10", "50", "100") // Quantity options

                                    var expandedPrice by remember { mutableStateOf(false) }
                                    var pricePerUnit by remember { mutableStateOf("0.00") } // Default price
                                    val priceOptions = listOf("1.00", "2.50", "5.00", "10.00") // Suggested price options

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        // Quantity Dropdown
                                        Box(
                                            modifier = Modifier
                                                .weight(1f)
                                                .clickable { expandedQuantity = true }
                                                .padding(8.dp)
                                        ) {
                                            Text(
                                                text = "Quantity: $quantity",
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold
                                            )

                                            DropdownMenu(
                                                expanded = expandedQuantity,
                                                onDismissRequest = { expandedQuantity = false }
                                            ) {
                                                quantityOptions.forEach { option ->
                                                    DropdownMenuItem(
                                                        text = { Text(option) },
                                                        onClick = {
                                                            quantity = option
                                                            expandedQuantity = false
                                                        }
                                                    )
                                                }
                                            }
                                        }

                                        Spacer(modifier = Modifier.width(8.dp))

                                        // Vertical Separator
                                        Box(
                                            modifier = Modifier
                                                .height(30.dp)
                                                .width(1.dp)
                                                .background(Color.Gray)
                                        )

                                        Spacer(modifier = Modifier.width(8.dp))

                                        // Price Dropdown with manual input
                                        Box(
                                            modifier = Modifier
                                                .weight(1f)
                                        ) {
                                            TextField(
                                                value = pricePerUnit,
                                                onValueChange = { pricePerUnit = it },
                                                label = { Text("Price per Unit") },
                                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                                singleLine = true,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .clickable { expandedPrice = true } // Clicking inside the box shows dropdown
                                            )

                                            DropdownMenu(
                                                expanded = expandedPrice,
                                                onDismissRequest = { expandedPrice = false }
                                            ) {
                                                priceOptions.forEach { option ->
                                                    DropdownMenuItem(
                                                        text = { Text("\$$option") },
                                                        onClick = {
                                                            pricePerUnit = option
                                                            expandedPrice = false
                                                        }
                                                    )
                                                }
                                            }
                                        }
                                    }





                                }
                                //End of Row


                            }



                        }
                        //End Of Card

                    }
                    //End of Box

                    Spacer(modifier = Modifier.height(10.dp))


                    Column (
                        modifier = Modifier.fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ){
































                    }
                }












            }
        }
    )

    //End of scaffold




}

@Preview(showBackground = true)
@Composable
fun PostScreenPreview(){
    PostScreen(rememberNavController())

}