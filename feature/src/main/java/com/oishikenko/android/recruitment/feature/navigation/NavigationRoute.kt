package com.oishikenko.android.recruitment.feature.navigation

sealed class NavigationRoute(var route: String) {
    object RecipeList   : NavigationRoute(route = "recipe_list")
    object RecipeDetail : NavigationRoute(route = "recipe_detail")
}