package com.example.mypracticeapp.ui.giphy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mypracticeapp.ui.giphy.GiphyListScreen
import com.example.mypracticeapp.ui.giphy.GiphyListViewModel

internal const val giphyListRoute = "giphy_list_route"

fun NavGraphBuilder.giphyListScreen(
    viewModel: GiphyListViewModel,
    onClickToDetail: (String) -> Unit
) {
    composable(
        route = giphyListRoute,
    ) {
        GiphyListScreen(
            viewModel = viewModel,
            onClickToDetail = onClickToDetail
        )
    }
}