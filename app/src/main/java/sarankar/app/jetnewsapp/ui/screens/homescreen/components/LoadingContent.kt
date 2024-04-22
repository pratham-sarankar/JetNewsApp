package sarankar.app.jetnewsapp.ui.screens.homescreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll

@ExperimentalMaterial3Api
@Composable
fun LoadingContent(
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    loading: Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit,
) {
    val state = rememberPullToRefreshState().apply {
        if(loading){
            startRefresh()
        }else{
            endRefresh()
        }
    }
    if(state.isRefreshing){
        LaunchedEffect(true) {
            onRefresh()
            state.endRefresh()
        }
    }
    if (empty) {
        emptyContent()
    } else {
        Box(
            modifier = Modifier.nestedScroll(state.nestedScrollConnection)
        ) {
            content()
            PullToRefreshContainer(
                state = state,
                modifier =  Modifier.align(Alignment.TopCenter)
            )
        }
    }
}