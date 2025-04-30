package com.chula.agrisnap.ui.screens.home

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chula.agrisnap.R
import com.chula.agrisnap.navigation.ROUT_GRAIN
import com.chula.agrisnap.ui.theme.neworange
import com.chula.agrisnap.ui.theme.plue
import com.chula.agrisnap.ui.theme.plue1




@Composable
fun HomeScreen(navController: NavController){


    //Scaffold

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(


        //BottomBar
        bottomBar = {
            NavigationBar(

            ){
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0

                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.AddCircle, contentDescription = "Favorites") },
                    label = { Text("Add") },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1

                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2
                        //  navController.navigate(ROUT_HOME)
                    }
                )

            }
        },

        //FloatingActionButton
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },

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
                Column (modifier = Modifier.fillMaxSize()

                ) {

                    //Box
                    Box() {
                        //Card
                        Card(
                            modifier = Modifier.fillMaxWidth().height(200.dp).padding(10.dp),
                            shape = RoundedCornerShape(topStart = 40.dp, topEnd  = 40.dp),
                            colors = CardDefaults.cardColors(neworange)
                        ) {


                            Spacer(modifier = Modifier.height(10.dp))


                            Column(
                                modifier = Modifier
                                    .fillMaxSize()

                                    .padding(16.dp), // Optional padding
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Image(
                                    painter = painterResource(R.drawable.agr),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(shape = CircleShape),
                                    contentScale = ContentScale.Crop
                                )

                                Text(
                                    text = "select the category",
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Row (){




                                    Spacer(modifier = Modifier.width(20.dp))







                                }


                            }


                        }
                        //End of Card


                    }
                    //End of Box

                    Spacer(modifier = Modifier.height(10.dp))








                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                                .align(alignment = Alignment.CenterHorizontally)
                                .padding(start = 20.dp, end = 20.dp)
                                .offset(y = 10.dp),
                            colors = CardDefaults.cardColors(plue)


                        ) {
                            //Contents of Card




                            Spacer(modifier = Modifier.width(10.dp))
                            Row () {

                                Column(modifier = Modifier.padding(start = 20.dp, top = 20.dp)) {
                                    Image(
                                        painter = painterResource(R.drawable.straw),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clip(CircleShape),
                                        contentScale = ContentScale.Crop,

                                    )
                                    Spacer(modifier = Modifier.height(5.dp))

                                    Text(
                                        text = "All categories you ",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.ExtraBold

                                    )
                                    Text(
                                        text = "can obtain them",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.ExtraBold

                                    )
                                    Text(
                                        text = "from here any time",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.ExtraBold

                                    )




                                    Spacer(modifier = Modifier.height(20.dp))





                                }
                                //End of Row

                                Row (modifier = Modifier.padding(start = 15.dp, top = 25.dp)){
                                    Image(
                                        painter = painterResource(R.drawable.straw),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(300.dp)
                                            .clip(CircleShape),
                                        contentScale = ContentScale.Crop
                                    )
                                }





                            }
                            Spacer(modifier = Modifier.height(50.dp))



                        }
                        //End Of Card


                        Spacer(modifier = Modifier.width(10.dp))

                    Column (
                        modifier = Modifier.fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ){

                        Spacer(modifier = Modifier.height(40.dp))
                        Text(
                            text = "Personal Tasks",
                            fontSize = 20.sp
                        )

                        //Row
                        Row(modifier = Modifier.padding(20.dp)) {


                            //Card1
                            Card(
                                modifier = Modifier
                                    .width(400.dp)
                                    .height(100.dp)
                                    .clickable {navController.navigate(ROUT_GRAIN) },
                                elevation = CardDefaults.cardElevation()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.Start,
                                    verticalArrangement = Arrangement.Center

                                ) {
                                    Row() {
                                        Image(
                                            painter = painterResource(R.drawable.agr),
                                            contentDescription = "home",
                                            modifier = Modifier.width(50.dp).padding(start = 10.dp)
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))

                                        Column() {
                                            Text(
                                                text = "Commodities we have",
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.ExtraBold

                                            )

                                            Text(
                                                text = "At any quantity",
                                                fontSize = 10.sp
                                            )
                                        }


                                    }


                                }


                            }
                        }


                        //Row
                        Row(modifier = Modifier.padding(20.dp)) {


                            //Card1
                            Card(
                                modifier = Modifier
                                    .width(400.dp)
                                    .height(100.dp)
                                    .clickable { },
                                elevation = CardDefaults.cardElevation()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.Start,
                                    verticalArrangement = Arrangement.Center

                                ) {
                                    Row() {
                                        Image(
                                            painter = painterResource(R.drawable.agr),
                                            contentDescription = "home",
                                            modifier = Modifier.width(50.dp).padding(start = 10.dp)
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))

                                        Column() {
                                            Text(
                                                text = "Livestock products",
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.ExtraBold

                                            )

                                            Text(
                                                text = "many in here",
                                                fontSize = 10.sp
                                            )
                                        }


                                    }


                                }


                            }
                        }


                        //Row
                        Row(modifier = Modifier.padding(20.dp)) {


                            //Card1
                            Card(
                                modifier = Modifier
                                    .width(400.dp)
                                    .height(100.dp)
                                    .clickable { },
                                elevation = CardDefaults.cardElevation()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.Start,
                                    verticalArrangement = Arrangement.Center

                                ) {
                                    Row() {
                                        Image(
                                            painter = painterResource(R.drawable.agr),
                                            contentDescription = "home",
                                            modifier = Modifier.width(50.dp).padding(start = 10.dp)
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))

                                        Column() {
                                            Text(
                                                text = "Equipments in here",
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.ExtraBold

                                            )

                                            Text(
                                                text = "Some in here",
                                                fontSize = 10.sp
                                            )
                                        }


                                    }


                                }


                            }
                        }


                        //Row
                        Row(modifier = Modifier.padding(20.dp)) {


                            //Card1
                            Card(
                                modifier = Modifier
                                    .width(400.dp)
                                    .height(100.dp)
                                    .clickable { },
                                elevation = CardDefaults.cardElevation()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.Start,
                                    verticalArrangement = Arrangement.Center

                                ) {
                                    Row() {
                                        Image(
                                            painter = painterResource(R.drawable.agr),
                                            contentDescription = "home",
                                            modifier = Modifier.width(50.dp).padding(start = 10.dp)
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))

                                        Column() {
                                            Text(
                                                text = "Fieldforce ",
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.ExtraBold

                                            )

                                            Text(
                                                text = "Some here",
                                                fontSize = 10.sp
                                            )
                                        }


                                    }


                                }


                            }
                        }


                        Row(modifier = Modifier.padding(20.dp)) {
                            Button(
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(containerColor = plue1),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp)
                            ) {
                                Text(text = "CONTINUE")
                            }

                        }






















                    }
                }












            }
        }
    )

    //End of scaffold




}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(rememberNavController())

}