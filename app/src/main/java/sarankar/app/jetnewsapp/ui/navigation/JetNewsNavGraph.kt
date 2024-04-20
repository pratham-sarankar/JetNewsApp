package sarankar.app.jetnewsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import sarankar.app.jetnewsapp.ui.screens.articlescren.ArticleScreen
import sarankar.app.jetnewsapp.ui.screens.homescreen.HomeScreen
import sarankar.app.jetnewsapp.ui.screens.interestsscreen.InterestsScreen

@Composable
fun JetNewsNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.startDestination()
    ) {
        composable(Screen.Home.route) {
            HomeScreen()
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