package sarankar.app.jetnewsapp.ui.screens.homescreen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import sarankar.app.jetnewsapp.ui.screens.homescreen.components.HomeScreenScaffold
import sarankar.app.jetnewsapp.ui.screens.homescreen.components.posts.PostListSimpleSection
import sarankar.app.jetnewsapp.ui.screens.homescreen.components.posts.PostListTopSection
import sarankar.app.jetnewsapp.ui.screens.homescreen.uistate.HomeUiState
import sarankar.app.jetnewsapp.ui.screens.homescreen.viewmodels.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    isExpandedScreen: Boolean,
    openDrawer: () -> Unit,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    val uiState: HomeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    HomeScreenScaffold(
        showTopAppBar = true,
        openDrawer = openDrawer,
        uiState = uiState,
        homeViewModel = homeViewModel,
        snackbarHostState = snackbarHostState,
    ) { state, contentPadding, modifier ->
        LazyColumn(
            modifier.padding(contentPadding)
        ) {
            item {
                PostListTopSection(
                    post = state.postsFeed.highlightedPost,
                    navigateToArticle = {
                        homeViewModel.selectArticle(it)
                    },
                )
            }
            if (state.postsFeed.recommendedPosts.isNotEmpty()) {
                item {
                    PostListSimpleSection(
                        posts = state.postsFeed.recommendedPosts,
                        navigateToArticle = {
                            homeViewModel.selectArticle(it)
                        },
                        favorites = state.favorites,
                        onToggleFavorite = {
                            homeViewModel.toggleFavorite(it)
                        }
                    )
                }
            }
        }
    }
}



