package sarankar.app.jetnewsapp.ui.screens.homescreen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll

@Composable
fun HomeScreen(){
    Scaffold {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenScaffold(
    modifier: Modifier = Modifier,
    showTopAppBar: Boolean,
){
    val topAppBarState = rememberTopAppBarState();
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior();
    Scaffold(
        topBar = {
            if(showTopAppBar){
                )
            }
        }
    ) { innerPadding ->
        val contentModifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)

    }

}

