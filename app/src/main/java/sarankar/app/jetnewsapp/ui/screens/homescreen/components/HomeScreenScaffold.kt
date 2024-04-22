package sarankar.app.jetnewsapp.ui.screens.homescreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import sarankar.app.jetnewsapp.R
import sarankar.app.jetnewsapp.ui.components.JetnewsSnackbarHost
import sarankar.app.jetnewsapp.ui.screens.homescreen.uistate.HomeUiState
import sarankar.app.jetnewsapp.ui.screens.homescreen.viewmodels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenScaffold(
    uiState: HomeUiState,
    modifier: Modifier = Modifier,
    showTopAppBar: Boolean = true,
    openDrawer: () -> Unit,
    homeViewModel: HomeViewModel,
    snackbarHostState: SnackbarHostState,
    postsContent: @Composable (
        uiState: HomeUiState.HasPosts,
        contentPadding: PaddingValues,
        modifier: Modifier
    ) -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)
    Scaffold(
        modifier = modifier,
        snackbarHost = {
            JetnewsSnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            if (showTopAppBar) {
                HomeTopAppBar(
                    openDrawer = openDrawer,
                    scrollBehavior = scrollBehavior,

                    )
            }
        }
    ) { innerPadding ->
        val contentModifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        LoadingContent(
            empty = when (uiState) {
                is HomeUiState.HasPosts -> false
                is HomeUiState.NoPosts -> uiState.isLoading
            },
            emptyContent = { FullScreenLoading() },
            onRefresh = { homeViewModel.refreshPosts() },
        ) {
            when (uiState) {
                is HomeUiState.HasPosts -> {
                    postsContent(uiState, innerPadding, contentModifier)
                }
                is HomeUiState.NoPosts -> {
                    if (uiState.errorMessages.isEmpty()) {
                        TextButton(
                            onClick = { homeViewModel.refreshPosts() },
                            modifier
                                .padding(innerPadding).fillMaxSize().wrapContentSize(Alignment.Center),
                        ) {
                            Text(
                                stringResource(id = R.string.home_tap_to_load_content),
                                textAlign = TextAlign.Center,
                            )
                        }
                    } else {
                        // there's currently an error showing, don't show any content
                        Box(
                            contentModifier
                                .padding(innerPadding)
                                .fillMaxSize()
                                .wrapContentSize(Alignment.Center)
                        ) {
                        }
                    }
                }
            }
        }

        // Process one error message at a time and show them as Snackbars in the UI
        if (uiState.errorMessages.isNotEmpty()) {
            // Remember the errorMessage to display on the screen
            val errorMessage = remember(uiState) { uiState.errorMessages[0] }

            // Get the text to show on the message from resources
            val errorMessageText: String = stringResource(errorMessage.messageId)
            val retryMessageText = stringResource(id = R.string.retry)

            // If onRefreshPosts or onErrorDismiss change while the LaunchedEffect is running,
            // don't restart the effect and use the latest lambda values.
            val onRefreshPostsState by rememberUpdatedState(homeViewModel::refreshPosts)
            val onErrorDismissState by rememberUpdatedState(homeViewModel::errorShown)

            // Effect running in a coroutine that displays the Snackbar on the screen
            // If there's a change to errorMessageText, retryMessageText or snackbarHostState,
            // the previous effect will be cancelled and a new one will start with the new values
            LaunchedEffect(errorMessageText, retryMessageText, snackbarHostState) {
                val snackbarResult = snackbarHostState.showSnackbar(
                    message = errorMessageText,
                    actionLabel = retryMessageText
                )
                if (snackbarResult == SnackbarResult.ActionPerformed) {
                    onRefreshPostsState()
                }
                // Once the message is displayed and dismissed, notify the ViewModel
                onErrorDismissState(errorMessage.id)
            }
        }
    }
}