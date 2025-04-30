package com.chula.agrisnap.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chula.agrisnap.ui.screens.auth.LoginScreen
import com.chula.agrisnap.ui.screens.auth.RegisterScreen
import com.chula.agrisnap.ui.screens.grain.GrainScreen
import com.chula.agrisnap.ui.screens.grain.PostScreen
import com.chula.agrisnap.ui.screens.home.HomeScreen
import com.chula.agrisnap.ui.screens.home.HomesScreen
import com.chula.agrisnap.ui.screens.splash.SplashScreen



@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_SPLASH) {
        SplashScreen(navController)
    }
        composable(ROUT_LOGIN) {
        LoginScreen(navController)
    }
        composable(ROUT_REGISTER) {
        RegisterScreen(navController)
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














    }
}