package com.chula.agrisnap.navigation


import RegisterScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chula.agrisnap.R
import com.chula.agrisnap.data.UserDatabase
import com.chula.agrisnap.model.CartItem
import com.chula.agrisnap.repository.UserRepository
import com.chula.agrisnap.ui.screens.cart.CartScreen
import com.chula.agrisnap.ui.screens.chats.ChatsScreen
import com.chula.agrisnap.ui.screens.checkout.CheckoutScreen
import com.chula.agrisnap.ui.screens.dairy.DairyScreen
import com.chula.agrisnap.ui.screens.fruit.FruitScreen
import com.chula.agrisnap.ui.screens.grain.GrainScreen
import com.chula.agrisnap.ui.screens.grain.PostScreen
import com.chula.agrisnap.ui.screens.home.HomeScreen
import com.chula.agrisnap.ui.screens.livestock.VegetableScreen
import com.chula.agrisnap.ui.screens.poultry.PoultryScreen
import com.chula.agrisnap.ui.screens.profile.ProfileScreen
import com.chula.agrisnap.ui.screens.splash.SplashScreen
import com.chula.agrisnap.ui.screens.starter.StaterScreen
import com.chula.agrisnap.viewmodel.AuthViewModel
import com.chula.agrisnaps.ui.screens.auth.LoginScreen
import com.chula.agrisnaps.ui.screens.chat.ChatScreen
import com.chula.agrisnaps.ui.screens.notification.NotificationScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_SPLASH) {
        SplashScreen(navController)
    }


        composable(ROUT_HOME) {
            HomeScreen(navController)
        }


        composable(ROUT_GRAIN) {
            GrainScreen(navController)
        }

        composable(ROUT_POST) {
            PostScreen(navController)
        }


        composable(ROUT_VEGETABLE) {
            VegetableScreen(navController)
        }

        composable(ROUT_STATER) {
            StaterScreen(navController)
        }
        composable(ROUT_NOTIFICATION) {
            NotificationScreen(navController)
        }

        composable(ROUT_PROFILE) {
           ProfileScreen(navController)
        }

        composable(ROUT_FRUIT) {
            FruitScreen(navController)
        }
        composable(ROUT_DAIRY) {
            DairyScreen(navController)
        }

        composable(ROUT_POULTRY) {
            PoultryScreen(navController)
        }

        composable(ROUT_CHECK) {
            CheckoutScreen(navController)
        }

        composable(ROUT_CHATS) {
            ChatsScreen(navController)
        }




        // Define the route for CartScreen
        composable(ROUT_CART) {
            // Pass a list of CartItems for CartScreen
            val sampleItems = listOf(
                CartItem("Banana", R.drawable.ban, 100, 2),
                CartItem("Burger", R.drawable.bar, 250, 1)
            )
            CartScreen(cartItems = sampleItems, navController = navController)
        }


        composable("chat/{receiverId}") { backStackEntry ->
            val receiverId = backStackEntry.arguments?.getString("receiverId") ?: ""
            ChatScreen(receiverId = receiverId, navController = navController)
        }



        //AUTHENTICATION

        // Initialize Room Database and Repository for Authentication
        val appDatabase = UserDatabase.getDatabase(context)
        val authRepository = UserRepository(appDatabase.userDao())
        val authViewModel: AuthViewModel = AuthViewModel(authRepository)
        composable(ROUT_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUT_LOGIN) {
                    popUpTo(ROUT_REGISTER) { inclusive = true }
                }
            }
        }

        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUT_HOME) {
                    popUpTo(ROUT_LOGIN) { inclusive = true }
                }
            }
        }














    }
}