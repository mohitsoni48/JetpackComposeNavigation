package com.mohitsoni.jetpackcomposenavigation

import android.content.Context
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import androidx.navigation.compose.composable
import kotlin.reflect.KClass

@Composable
fun NavHost(
    navController: SafeNavHostController,
    startDestination: Route,
    navGraph: NavGraph,
    modifier: Modifier = Modifier,
    enterTransition: EnterTransition = EnterTransition.None,
    exitTransition: ExitTransition = ExitTransition.None,
    builder: NavGraphBuilder.() -> Unit
) {
    navController.NavHost(
        startDestination = startDestination,
        navGraph = navGraph,
        modifier = modifier,
        builder = builder,
        enterTransition = enterTransition ,
        exitTransition = exitTransition
    )
}

class SafeNavHostController (context: Context, private val routerClass: Class<*>, private val navHostController: NavHostController) {
    @Composable
    fun NavHost(
        startDestination: Route,
        navGraph: NavGraph,
        modifier: Modifier = Modifier,
        enterTransition: EnterTransition = EnterTransition.None,
        exitTransition: ExitTransition = ExitTransition.None,
        builder: NavGraphBuilder.() -> Unit
    ) {
        androidx.navigation.compose.NavHost(
            navController = navHostController,
            startDestination = startDestination.key,
            modifier = modifier,
            builder = builder,
            enterTransition = { enterTransition },
            exitTransition = { exitTransition }
        )
    }

    fun <T> navigate(route: )
}

@Composable
inline fun <reified T : Route> rememberNavController(
    routerClass: Class<T>,
    vararg navigators: Navigator<out NavDestination>
) : SafeNavHostController {
    val context = LocalContext.current
    return SafeNavHostController(context, routerClass, androidx.navigation.compose.rememberNavController(*navigators))
}

fun NavGraphBuilder.composable(
    route: Route,
    enterTransition: EnterTransition = EnterTransition.None,
    exitTransition: ExitTransition = ExitTransition.None,
    content: @Composable (AnimatedContentScope.(NavBackStackEntry) -> Unit)
) {
    composable(
        route = route.key,
        content = content,
        enterTransition = { enterTransition } ,
        exitTransition = { exitTransition }
    )
}

fun <T: Route> NavHostController.navigateAndPopUpToRoot(destination: T) {
    this.navigate(destination.key) {
        if (destination.clearBackStack) {
            popUpTo(0) {
                inclusive = true
            }
        }
    }
}

fun <T: Route> NavHostController.navigateAndPopBackStack(destination: T) {
    val currentRoute = this.currentDestination?.route
    this.navigate(destination.key) {
        currentRoute?.let {
            popUpTo(it) {
                inclusive = true
            }
        }
    }
}

fun <T: Route> NavHostController.navigate(destination: T) {
    this.navigate(destination.key)
}

abstract class Route(open val key: String, open val clearBackStack: Boolean)

abstract class NavGraph(private val routerClass: KClass<Route>) {
    fun getAllRoutes() = routerClass.sealedSubclasses.map { it.simpleName }
}