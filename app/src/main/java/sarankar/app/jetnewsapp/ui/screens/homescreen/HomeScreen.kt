package sarankar.app.jetnewsapp.ui.screens.homescreen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import sarankar.app.jetnewsapp.ui.screens.homescreen.components.HomeScreenScaffold
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
    ){ uiState, contentPadding, modifier ->
        Box(modifier = modifier.padding(contentPadding)){
            LazyColumn {
                items(10){ post ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 180.dp)
                            .padding(16.dp)
                            .clickable {
                                Log.d("HomeScreen", "Post clicked: ${post}")
                            }
                    ){
                        Text(text = "Post $post")
                    }
                }
            }
        }
    }
}



