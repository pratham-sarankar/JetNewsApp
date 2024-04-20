package sarankar.app.jetnewsapp.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

sealed class Screen(val route: String){
    data object Home: Screen("home")
    data object Interests: Screen("interests")
    data object Article: Screen("article/{postId}"){
        fun createRoute(postId: String): String {
            return "article/$postId"
        }
    }

    companion object{
        fun startDestination(): String {
            return Home.route
        }
    }
}


class JetNewsNavigator(private val navController: NavHostController) {
    fun navigateToHome() {
        navController.navigate(Screen.Home.route){
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }

    fun navigateToInterests() {
        navController.navigate(Screen.Interests.route){
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }

    fun navigateToArticle(postId: String) {
        navController.navigate(Screen.Article.createRoute(postId)){
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}