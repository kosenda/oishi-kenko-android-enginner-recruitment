package com.oishikenko.android.recruitment.feature.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.oishikenko.android.recruitment.feature.list.RecipeDetailScreen
import com.oishikenko.android.recruitment.feature.list.RecipeListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination =  NavigationRoute.RecipeList.route
    ) {
        composable(route = NavigationRoute.RecipeList.route) {
            RecipeListScreen(navController = navController)
        }
        composable(
            route = NavigationRoute.RecipeDetail.route +
                "/{image_url}/{comment}/{recipe_type}/{record_at}",
            arguments = listOf(
                navArgument("image_url")   { type = NavType.StringType },
                navArgument("comment")     { type = NavType.StringType },
                navArgument("recipe_type") { type = NavType.StringType },
                navArgument("record_at")   { type = NavType.StringType }
            )
        ) {
            RecipeDetailScreen(
                image_url   = it.arguments?.getString("image_url")   ?: "",
                comment     = it.arguments?.getString("comment")     ?: "",
                recipe_type = it.arguments?.getString("recipe_type") ?: "",
                record_at   = it.arguments?.getString("record_at")   ?: "",
                navController = navController
            )
        }
    }
}
