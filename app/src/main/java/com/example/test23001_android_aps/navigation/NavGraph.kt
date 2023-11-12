package com.example.test23001_android_aps.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.test23001_android_aps.screen.AllergicScreen
import com.example.test23001_android_aps.screen.DietScreen
import com.example.test23001_android_aps.screen.HomeScreen
import com.example.test23001_android_aps.screen.PrioritizeScreen
import com.example.test23001_android_aps.screen.Screen
import com.example.test23001_android_aps.screen.SurveyScreen
import com.example.test23001_android_aps.viewmodel.MainViewModel

@Composable
fun SetupNavGraph(navController: NavHostController){
    val mainViewModel = hiltViewModel<MainViewModel>()
    NavHost(navController = navController, startDestination = Screen.Welcome.route){

        composable(route = Screen.Welcome.route){
            HomeScreen(navController = navController,mainViewModel)
        }

        composable(route = Screen.Prioritize.route){
            PrioritizeScreen(navController = navController,mainViewModel)
        }

        composable(route = Screen.Diet.route){
            DietScreen(navController = navController,mainViewModel)
        }

        composable(route = Screen.Allergic.route){
            AllergicScreen(navController = navController,mainViewModel)
        }

        composable(route = Screen.Survey.route){
            SurveyScreen(navController = navController, mainViewModel = mainViewModel)
        }
    }
}