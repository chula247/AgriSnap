package com.chula.agrisnap.ui.screens.grain


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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.chula.agrisnap.navigation.ROUT_POST
import com.chula.agrisnap.ui.theme.neworange

@Composable
fun GrainScreen(navController: NavController){


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
                                    text = "select the Sub-Category",
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




                    Spacer(modifier = Modifier.width(10.dp))

                    Column (
                        modifier = Modifier.fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ){

                        //Row
                        Row(modifier = Modifier.padding(20.dp)) {

                            //Card1
                            Card(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(120.dp)
                                    .clickable {navController.navigate(ROUT_POST) },
                                elevation = CardDefaults.cardElevation()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.teff),
                                        contentDescription = "home",
                                        modifier = Modifier.size(90.dp)
                                    )
                                    Row () {

                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(text = "Teff", fontSize = 20.sp)
                                    }
                                }


                            }

                            //End of Card1
                            Spacer(modifier = Modifier.width(20.dp))

                            //Card2
                            Card(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(120.dp)
                                    .clickable { navController.navigate(ROUT_POST)},
                                elevation = CardDefaults.cardElevation()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.wheat),
                                        contentDescription = "home",
                                        modifier = Modifier.size(90.dp)
                                    )
                                    Row () {

                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(text = "Wheat", fontSize = 20.sp)
                                    }

                                }


                            }

                            //End of Card2

                            Spacer(modifier = Modifier.width(20.dp))
                            //Card2
                            Card(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(120.dp)
                                    .clickable {navController.navigate(ROUT_POST) },
                                elevation = CardDefaults.cardElevation()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.oats),
                                        contentDescription = "home",
                                        modifier = Modifier.size(90.dp)
                                    )
                                    Row () {

                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(text = "Oats", fontSize = 20.sp)
                                    }

                                }


                            }

                            //End of Card2


                        }

                        //End of Row

                        //Row
                        Row(modifier = Modifier.padding(20.dp)) {

                            //Card1
                            Card(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(120.dp)
                                    .clickable { navController.navigate(ROUT_POST)},
                                elevation = CardDefaults.cardElevation()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.rice),
                                        contentDescription = "home",
                                        modifier = Modifier.size(90.dp)
                                    )
                                    Row () {

                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(text = "Rice", fontSize = 20.sp)
                                    }
                                }


                            }

                            //End of Card1
                            Spacer(modifier = Modifier.width(20.dp))

                            //Card2
                            Card(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(120.dp)
                                    .clickable { navController.navigate(ROUT_POST)},
                                elevation = CardDefaults.cardElevation()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.corn),
                                        contentDescription = "home",
                                        modifier = Modifier.size(90.dp)
                                    )
                                    Row () {

                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(text = "Corn", fontSize = 20.sp)
                                    }

                                }


                            }

                            //End of Card2

                            Spacer(modifier = Modifier.width(20.dp))
                            //Card2
                            Card(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(120.dp)
                                    .clickable { navController.navigate(ROUT_POST)},
                                elevation = CardDefaults.cardElevation()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.barley),
                                        contentDescription = "home",
                                        modifier = Modifier.size(90.dp)
                                    )
                                    Row () {

                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(text = "Barley", fontSize = 20.sp)
                                    }

                                }


                            }

                            //End of Card2


                        }

                        //End of Row

                        //Row
                        Row(modifier = Modifier.padding(20.dp)) {

                            //Card1
                            Card(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(120.dp)
                                    .clickable {navController.navigate(ROUT_POST) },
                                elevation = CardDefaults.cardElevation()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.sorghum),
                                        contentDescription = "home",
                                        modifier = Modifier.size(90.dp)
                                    )
                                    Row () {

                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(text = "Sorghum", fontSize = 20.sp)
                                    }
                                }


                            }

                            //End of Card1
                            Spacer(modifier = Modifier.width(20.dp))

                            //Card2
                            Card(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(120.dp)
                                    .clickable {navController.navigate(ROUT_POST) },
                                elevation = CardDefaults.cardElevation()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.rye),
                                        contentDescription = "home",
                                        modifier = Modifier.size(90.dp)
                                    )
                                    Row () {

                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(text = "Rye", fontSize = 20.sp)
                                    }

                                }


                            }

                            //End of Card2

                            Spacer(modifier = Modifier.width(20.dp))
                            //Card2
                            Card(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(120.dp)
                                    .clickable {navController.navigate(ROUT_POST) },
                                elevation = CardDefaults.cardElevation()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.millet),
                                        contentDescription = "home",
                                        modifier = Modifier.size(90.dp)
                                    )
                                    Row () {

                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(text = "Millet", fontSize = 20.sp)
                                    }

                                }


                            }

                            //End of Card2


                        }

                        //End of Row

                        //Row
                        Row(modifier = Modifier.padding(20.dp)) {

                            //Card1
                            Card(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(120.dp)
                                    .clickable {navController.navigate(ROUT_POST) },
                                elevation = CardDefaults.cardElevation()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.triti),
                                        contentDescription = "home",
                                        modifier = Modifier.size(90.dp)
                                    )
                                    Row () {

                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(text = "Triticale", fontSize = 20.sp)
                                    }
                                }


                            }

                            //End of Card1
                            Spacer(modifier = Modifier.width(20.dp))

                            //Card2
                            Card(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(120.dp)
                                    .clickable {navController.navigate(ROUT_POST) },
                                elevation = CardDefaults.cardElevation()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.amar),
                                        contentDescription = "home",
                                        modifier = Modifier.size(90.dp)
                                    )
                                    Row () {

                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(text = "Amaranth", fontSize = 20.sp)
                                    }

                                }


                            }

                            //End of Card2

                            Spacer(modifier = Modifier.width(20.dp))
                            //Card2
                            Card(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(120.dp)
                                    .clickable {navController.navigate(ROUT_POST) },
                                elevation = CardDefaults.cardElevation()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.buck),
                                        contentDescription = "home",
                                        modifier = Modifier.size(90.dp)
                                    )
                                    Row () {

                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(text = "Buckwheat", fontSize = 15.sp)
                                    }

                                }


                            }

                            //End of Card2


                        }

                        //End of Row

                        //Row
                        Row(modifier = Modifier.padding(20.dp)) {

                            //Card1
                            Card(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(120.dp)
                                    .clickable { navController.navigate(ROUT_POST)},
                                elevation = CardDefaults.cardElevation()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.qui),
                                        contentDescription = "home",
                                        modifier = Modifier.size(90.dp)
                                    )
                                    Row () {

                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(text = "Quinoa", fontSize = 20.sp)
                                    }
                                }


                            }

                            //End of Card1
                            Spacer(modifier = Modifier.width(20.dp))

                            //Card2
                            Card(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(120.dp)
                                    .clickable {navController.navigate(ROUT_POST) },
                                elevation = CardDefaults.cardElevation()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.spe),
                                        contentDescription = "home",
                                        modifier = Modifier.size(90.dp)
                                    )
                                    Row () {

                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(text = "Spelt", fontSize = 20.sp)
                                    }

                                }


                            }

                            //End of Card2

                            Spacer(modifier = Modifier.width(20.dp))
                            //Card2
                            Card(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(120.dp)
                                    .clickable {navController.navigate(ROUT_POST) },
                                elevation = CardDefaults.cardElevation()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.fon),
                                        contentDescription = "home",
                                        modifier = Modifier.size(90.dp)
                                    )
                                    Row () {

                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(text = "Fonio", fontSize = 20.sp)
                                    }

                                }


                            }

                            //End of Card2


                        }

                        //End of Row










//end
                    }
                }












            }
        }
    )

    //End of scaffold




}

@Preview(showBackground = true)
@Composable
fun GrainScreenPreview(){
    GrainScreen(rememberNavController())

}