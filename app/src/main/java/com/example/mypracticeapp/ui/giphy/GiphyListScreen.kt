package com.example.mypracticeapp.ui.giphy

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.domain.model.GifModel
import com.example.mypracticeapp.R

@Composable
fun GiphyListScreen(
    viewModel: GiphyListViewModel,
    onClickToDetail: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Content(
        uiState = uiState,
        onClickToDetail = onClickToDetail,
        onLoadMore = { viewModel.getTrendingGifs() }
    )
}


@Composable
private fun Content(
    uiState: GiphyListUiState,
    onClickToDetail: (String) -> Unit,
    onLoadMore: () -> Unit
) {

    when(uiState) {

        is GiphyListUiState.Loading -> {
            LoadingScreen()
        }

        is GiphyListUiState.Error -> {

        }

        is GiphyListUiState.Success -> {
            GiphyListContent(
                uiModel = uiState.uiModel,
                onClickToDetail = onClickToDetail,
                onLoadMore = onLoadMore
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GiphyListContent(
    uiModel: GiphyListUiModel,
    onClickToDetail: (String) -> Unit,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            Column(
                modifier = Modifier
            ) {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.app_bar_title)
                        )
                    },
                )
                Divider(
                    modifier = Modifier,
                    color = Color.LightGray,
                    thickness = 1.dp
                )
            }
        }
    ) { paddingValues ->
        val listState = rememberLazyGridState()

        val reachedBottom by remember {
            derivedStateOf {
                val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
                lastVisibleItem?.index != 0 && lastVisibleItem?.index == listState.layoutInfo.totalItemsCount - 1
            }
        }
        
        LaunchedEffect(key1 = reachedBottom) {
            if (reachedBottom) {
                onLoadMore()
            }
        }

        LazyVerticalGrid(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            columns = GridCells.Fixed(2),
            state = listState,
            content = {
                items(uiModel.gifs) { gif ->
                    GiphyListItem(
                        model = gif,
                        onClickToDetail = onClickToDetail
                    )
                }
                if (reachedBottom) {
                    item {
                        Box(
                            modifier = Modifier,
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        )

    }
}

@Composable
private fun GiphyListItem(
    model: GifModel,
    onClickToDetail: (String) -> Unit
) {

    AsyncImage(
        model = model.images.fixed_height_downsampled.url,
        contentDescription = null,
        modifier = Modifier
            .size(200.dp)
            .padding(10.dp)
            .clickable {
                onClickToDetail(model.images.original.url)
            }
    )
}

@Composable
private fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun GiphyListPreview() {
    MaterialTheme {
        GiphyListContent(
            uiModel = GiphyListUiModel.previewData(),
            onClickToDetail = {},
            onLoadMore = {}
        )
    }
}