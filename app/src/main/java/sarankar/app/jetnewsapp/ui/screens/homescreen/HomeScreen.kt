package sarankar.app.jetnewsapp.ui.screens.homescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sarankar.app.jetnewsapp.ui.screens.homescreen.components.LoadingContent

@Composable
fun HomeScreen(){
    HomeScreenScaffold(
        showTopAppBar = true
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun HomeScreenScaffold(
    modifier: Modifier = Modifier,
    showTopAppBar: Boolean = true,
){
    val topAppBarState = rememberTopAppBarState();
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior();
    var isLoading by remember {
        mutableStateOf(false)
    }
    Scaffold {
        Box(modifier = Modifier.padding(it)) {
            LoadingContent(
                empty = false,
                emptyContent = { /*TODO*/ },
                loading = isLoading,
                onRefresh = { /*TODO*/ }) {
                LazyColumn {
                    items(20) {
                        Card(
                            modifier = Modifier.fillMaxWidth().height(100.dp).padding(8.dp),
                        ) {
                            Text(text = "Item $it")
                        }
                    }
                }
            }

        }
    }
}

