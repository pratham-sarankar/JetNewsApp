package sarankar.app.jetnewsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import sarankar.app.jetnewsapp.data.AppContainer
import sarankar.app.jetnewsapp.ui.screens.articlescren.ArticleScreen
import sarankar.app.jetnewsapp.ui.screens.homescreen.HomeScreen
import sarankar.app.jetnewsapp.ui.screens.homescreen.viewmodels.HomeViewModel
import sarankar.app.jetnewsapp.ui.screens.interestsscreen.InterestsScreen

const val POST_ID = "postId"

@Composable
fun JetNewsNavGraph(
    modifier: Modifier = Modifier,
    appContainer: AppContainer,
    navController: NavHostController,
    isExpandedScreen: Boolean,
    startDestination: String,
    openDrawer: () -> Unit = {},
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(Screen.Home.route) {navBackStackEntry->
            val homeViewModel: HomeViewModel = viewModel (
                factory = HomeViewModel.provideFactory(
                    postsRepository = appContainer.postsRepository,
                    preSelectedPostId = navBackStackEntry.arguments?.getString(POST_ID)
                )
            )
            HomeScreen(
                homeViewModel = homeViewModel,
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer,
            )
        }
        composable(Screen.Interests.route) {
            InterestsScreen()
        }
        composable(Screen.Article.route, arguments = listOf(
            navArgument("postId") {
                type = NavType.StringType
            }
        )) {
            ArticleScreen()
        }
    }
}