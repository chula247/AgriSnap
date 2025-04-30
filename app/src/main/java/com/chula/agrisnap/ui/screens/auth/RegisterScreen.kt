package com.chula.agrisnap.ui.screens.auth

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chula.agrisnap.R
import com.chula.agrisnap.navigation.ROUT_HOME
import com.chula.agrisnap.navigation.ROUT_HOMES
import com.chula.agrisnap.navigation.ROUT_REGISTER
import com.chula.agrisnap.ui.theme.blue1
import com.chula.agrisnap.ui.theme.neworange
import com.chula.agrisnap.ui.theme.newwhite

@Composable
fun RegisterScreen(navController: NavController){

    Column (
        modifier = Modifier.fillMaxSize(),

        ){

        //Box
        Box (){
            //Card
            Card (
                modifier = Modifier.fillMaxWidth().height(500.dp),
                shape = RoundedCornerShape(bottomStart = 60.dp, bottomEnd = 60.dp),
                colors = CardDefaults.cardColors(blue1)
            ){
                Spacer(modifier = Modifier.height(15.dp))
                Row(modifier = Modifier.padding(20.dp)) {


                    //Row
                    Row (modifier = Modifier.padding(start = 20.dp).padding(start = 30.dp)) {


                        Image(
                            painter = painterResource(R.drawable.agr),
                            contentDescription = "",
                            modifier = Modifier.width(50.dp).height(30.dp),
                            contentScale = ContentScale.FillWidth,

                            )

                        Spacer(modifier = Modifier.width(10.dp))

                        Column() {

                            Text(
                                text = "LOGO APP",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = newwhite

                            )


                        }


                        Spacer(modifier = Modifier.height(40.dp))



                    }
                    //End of Row
                }
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ){

                    Image(
                        painter = painterResource(R.drawable.shopping),
                        contentDescription = "home",
                        modifier = Modifier
                            .width(400.dp)
                            .height(300.dp),
                        contentScale = ContentScale.FillBounds
                    )
                }





            }
            //End of Card

            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .align(alignment = Alignment.BottomEnd)
                    .offset(y = 350.dp),
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                colors = CardDefaults.cardColors()


            ){
                //Contents of Card




                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,


                    ) {

                    Row {
                        Text(
                            text = "The Most Worth",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = neworange


                        )
                    }


                    Row {
                        Text(
                            text = "Grocery App",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = neworange

                        )
                    }

                    var username by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }

                    Spacer(modifier = Modifier.height(20.dp))

                    //userName
                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it},
                        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),

                        label = {
                            Text(
                                text = "username",
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = neworange,
                            focusedLabelColor = Color.Gray,
                            unfocusedBorderColor = Color.Black
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)


                    )

                    //End of username
                    Spacer(modifier = Modifier.height(20.dp))

                    //Password
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it},
                        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
                        label = { Text(text = "Password") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = neworange,
                            focusedLabelColor = Color.Gray,
                            unfocusedBorderColor = Color.Black
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation()


                    )

                    //End of Password

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(onClick = {
                        navController.navigate(ROUT_HOMES)
                    },
                        colors = ButtonDefaults.buttonColors(neworange),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)

                    ) {
                        Text(text = "Sign Up")
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row (){

                        Text(
                            text = "Already have an account?",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold

                        )


                        Spacer(modifier = Modifier.width(10.dp))

                        Column () {
                            Text(
                                text = "Log In",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = neworange

                            )
                        }



                    }







                }



                //End of Row






            }

        }
        //End of Box



    }


}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview(){
    RegisterScreen(rememberNavController())

}