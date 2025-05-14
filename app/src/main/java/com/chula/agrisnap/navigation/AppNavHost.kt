package com.chula.agrisnap.navigation


import RegisterScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.chula.agrisnap.data.UserDatabase
import com.chula.agrisnap.repository.UserRepository
import com.chula.agrisnap.ui.screens.auth.LoginScreen
import com.chula.agrisnap.ui.screens.chats.ChatsScreen
import com.chula.agrisnap.ui.screens.checkout.CheckoutScreen
import com.chula.agrisnap.ui.screens.dairy.AddDairyScreen
import com.chula.agrisnap.ui.screens.dairy.DairyListScreen
import com.chula.agrisnap.ui.screens.dairy.DairyScreen
import com.chula.agrisnap.ui.screens.dairy.EditDairyScreen
import com.chula.agrisnap.ui.screens.fruit.AddFruitScreen
import com.chula.agrisnap.ui.screens.fruit.EditFruitScreen
import com.chula.agrisnap.ui.screens.fruits.FruitListScreen
import com.chula.agrisnap.ui.screens.fruits.FruitScreen
import com.chula.agrisnap.ui.screens.grain.AddGrainScreen
import com.chula.agrisnap.ui.screens.grain.EditGrainScreen
import com.chula.agrisnap.ui.screens.grain.GrainListScreen
import com.chula.agrisnap.ui.screens.grain.GrainScreen
import com.chula.agrisnap.ui.screens.home.HomeScreen
import com.chula.agrisnap.ui.screens.offer.AddOfferScreen
import com.chula.agrisnap.ui.screens.offer.EditOfferScreen
import com.chula.agrisnap.ui.screens.offer.OfferListScreen
import com.chula.agrisnap.ui.screens.offer.OfferScreen
import com.chula.agrisnap.ui.screens.vegetables.VegetableScreen

import com.chula.agrisnap.ui.screens.profile.ProfileScreen

import com.chula.agrisnap.ui.screens.splash.SplashScreen
import com.chula.agrisnap.ui.screens.starter.StaterScreen
import com.chula.agrisnap.ui.screens.state.StateScreen
import com.chula.agrisnap.ui.screens.vegetable.AddVegetableScreen
import com.chula.agrisnap.ui.screens.vegetable.EditVegetableScreen
import com.chula.agrisnap.ui.screens.vegetables.VegetableListScreen
import com.chula.agrisnap.viewmodel.AuthViewModel
import com.chula.agrisnap.viewmodel.DairyViewModel
import com.chula.agrisnap.viewmodel.FruitViewModel
import com.chula.agrisnap.viewmodel.GrainViewModel
import com.chula.agrisnap.viewmodel.OfferViewModel

import com.chula.agrisnap.viewmodel.VegetableViewModel
import com.chula.agrisnaps.ui.screens.chat.ChatScreen
import com.chula.agrisnaps.ui.screens.notification.NotificationScreen


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH,
    vegetableViewModel: VegetableViewModel = viewModel(),
    dairyViewModel: DairyViewModel = viewModel(),
    fruitViewModel: FruitViewModel = viewModel(),
    offerViewModel: OfferViewModel = viewModel(),
    grainViewModel: GrainViewModel = viewModel(),


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




        composable(ROUT_VEGETABLE) {
            VegetableScreen(navController)
        }
        composable(ROUTE_OFFER) {
            OfferScreen(navController)
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


        composable(ROUT_DAIRY) {
            DairyScreen(navController)
        }



        composable(ROUT_CHECK) {
            CheckoutScreen(navController)
        }
        composable(ROUT_STATE) {
            StateScreen(navController)
        }

        composable(ROUT_CHATS) {
            ChatsScreen(navController = navController)
        }
        composable(ROUT_FRUIT) {
            FruitScreen(navController = navController)
        }
        composable(ROUT_GRAIN) {
            GrainScreen(navController = navController)
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
            LoginScreen (authViewModel, navController) {
                navController.navigate(ROUT_HOME) {
                    popUpTo(ROUT_LOGIN) { inclusive = true }
                }
            }
        }

        // VEGETABLES
        composable(ROUT_ADD_VEGETABLE) {
            AddVegetableScreen(navController, vegetableViewModel)
        }

        composable(ROUT_VEGETABLE_LIST) {
            VegetableListScreen(navController, vegetableViewModel)
        }

        composable(
            route = ROUT_EDIT_VEGETABLE,
            arguments = listOf(navArgument("vegetableId") { type = NavType.IntType })
        ) { backStackEntry ->
            val vegetableId = backStackEntry.arguments?.getInt("vegetableId")
            if (vegetableId != null) {
                EditVegetableScreen(vegetableId, navController, vegetableViewModel)
            }
        }
        // DAIRY
        composable(ROUT_ADD_DAIRY) {
            AddDairyScreen(navController, dairyViewModel)
        }

        composable(ROUT_DAIRY_LIST) {
            DairyListScreen(navController, dairyViewModel)
        }

        composable(
            route = ROUT_EDIT_DAIRY,
            arguments = listOf(navArgument("dairyId") { type = NavType.IntType })
        ) { backStackEntry ->
            val dairyId = backStackEntry.arguments?.getInt("dairyId")
            if (dairyId != null) {
                EditDairyScreen(dairyId, navController, dairyViewModel)
            }
        }

        // GRAIN
        composable(ROUT_ADD_GRAIN) {
            AddGrainScreen(navController, grainViewModel)
        }

        composable(ROUT_GRAIN_LIST) {
            GrainListScreen(navController, grainViewModel)
        }

        composable(
            route = ROUT_EDIT_GRAIN,
            arguments = listOf(navArgument("grainId") { type = NavType.IntType })
        ) { backStackEntry ->
            val grainId = backStackEntry.arguments?.getInt("grainId")
            if (grainId != null) {
                EditGrainScreen(grainId, navController, grainViewModel)
            }
        }

        // FRUITS
        composable(ROUT_ADD_FRUIT) {
            AddFruitScreen(navController, fruitViewModel)
        }

        composable(ROUT_FRUIT_LIST) {
            FruitListScreen(navController, fruitViewModel)
        }

        composable(
            route = ROUT_EDIT_FRUIT,
            arguments = listOf(navArgument("fruitId") { type = NavType.IntType })
        ) { backStackEntry ->
            val fruitId = backStackEntry.arguments?.getInt("fruitId")
            if (fruitId != null) {
                EditFruitScreen(fruitId, navController, fruitViewModel)
            }
        }

        // OFFERS
        composable(ROUT_ADD_OFFER) {
            AddOfferScreen(navController, offerViewModel)
        }

        composable(ROUT_OFFER_LIST) {
            OfferListScreen(navController, offerViewModel)
        }

        composable(
            route = ROUT_EDIT_OFFER,
            arguments = listOf(navArgument("offerId") { type = NavType.IntType })
        ) { backStackEntry ->
            val offerId = backStackEntry.arguments?.getInt("offerId")
            if (offerId != null) {
                EditOfferScreen(offerId, navController, offerViewModel)
            }
        }





    }
}