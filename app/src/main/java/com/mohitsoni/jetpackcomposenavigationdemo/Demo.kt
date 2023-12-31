package com.mohitsoni.jetpackcomposenavigationdemo

import androidx.compose.runtime.Composable
import com.mohitsoni.jetpackcomposenavigation.Route
import com.mohitsoni.jetpackcomposenavigation.rememberNavController

@Composable
fun Demo() {
    val navController = rememberNavController(routerClass = DemoRouter::class.java)

    navController.navHostController.navigate()
}

sealed class DemoRouter(override val key: String, override val clearBackStack: Boolean): Route(key, clearBackStack) {
    data object DemoRoute1 : DemoRouter("demo_1", true)
}