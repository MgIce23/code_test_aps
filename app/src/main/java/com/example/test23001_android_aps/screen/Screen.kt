package com.example.test23001_android_aps.screen

sealed class Screen(val route:String){

    object Welcome : Screen("welcome")
    object Prioritize : Screen ("prioritize")
    object Diet : Screen("diet")
    object Survey: Screen ("survey")
    object Allergic : Screen("allergic")
}
