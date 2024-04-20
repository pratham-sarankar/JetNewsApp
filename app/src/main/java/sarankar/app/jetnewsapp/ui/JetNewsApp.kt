package sarankar.app.jetnewsapp.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import sarankar.app.jetnewsapp.ui.components.navigation.AppDrawer
import sarankar.app.jetnewsapp.ui.components.navigation.AppNavRail
import sarankar.app.jetnewsapp.ui.navigation.JetNewsNavGraph
import sarankar.app.jetnewsapp.ui.navigation.JetNewsNavigator
import sarankar.app.jetnewsapp.ui.navigation.Screen
import sarankar.app.jetnewsapp.ui.theme.JetNewsAppTheme

@Composable
fun JetNewsApp(
    widthSizeClass: WindowWidthSizeClass
) {
    JetNewsAppTheme {
        val navController = rememberNavController()
        val navigator = remember(navController) {
            JetNewsNavigator(navController)
        }

        val coroutineScope = rememberCoroutineScope()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Home.route;

        val isExpandedScreen = widthSizeClass == WindowWidthSizeClass.Expanded
        val sizeAwareDrawerState = rememberSizeAwareDrawerState(isExpandedScreen)

        ModalNavigationDrawer(
            drawerContent = {
                AppDrawer(
                    currentRoute = currentRoute, navigator = navigator,
                    closeDrawer = {
                        coroutineScope.launch {
                            sizeAwareDrawerState.close();
                        }
                    },
                )
            },
            drawerState = sizeAwareDrawerState,
            gesturesEnabled = !isExpandedScreen
        ) {
            Row {
                if (isExpandedScreen){
                    AppNavRail(currentRoute = currentRoute, navigator = navigator)
                }
                JetNewsNavGraph(
                    navController = navController,
                )
            }
        }

    }
}

@Composable
fun rememberSizeAwareDrawerState(isExpandedScreen: Boolean): DrawerState {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    return if (!isExpandedScreen) {
        drawerState
    } else {
        DrawerState(DrawerValue.Closed);
    }
}