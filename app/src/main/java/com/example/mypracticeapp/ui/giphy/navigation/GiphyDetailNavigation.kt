package com.example.mypracticeapp.ui.giphy.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mypracticeapp.ui.giphy.GiphyDetailScreen

internal const val giphyDetailRoute = "giphy_detail_route"
internal const val giphyDetailUrlArg = "giphy_detail_url_arg"

fun NavGraphBuilder.giphyDetailScreen(
    onBackPressed: () -> Unit
) {
    composable(
        route = "$giphyDetailRoute/{$giphyDetailUrlArg}",
        arguments = listOf(navArgument(giphyDetailUrlArg) { type = NavType.StringType })
    ) { backStackEntry ->
        GiphyDetailScreen(
            gifUrl = backStackEntry.arguments?.getString(giphyDetailUrlArg).orEmpty(),
            onBackPressed = onBackPressed
        )
    }
}