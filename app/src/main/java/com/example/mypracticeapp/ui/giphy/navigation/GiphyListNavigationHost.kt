package com.example.mypracticeapp.ui.giphy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.mypracticeapp.ui.giphy.GiphyListViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun GiphyListNavigationHost(
    viewModel: GiphyListViewModel
) {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = giphyListRoute,
    ) {

        giphyListScreen(
            viewModel = viewModel,
            onClickToDetail = { url ->
                val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString()).orEmpty()
                navController.navigate("$giphyDetailRoute/$encodedUrl")
            }
        )

        giphyDetailScreen(
            onBackPressed = {
                navController.popBackStack()
            }
        )
    }
}