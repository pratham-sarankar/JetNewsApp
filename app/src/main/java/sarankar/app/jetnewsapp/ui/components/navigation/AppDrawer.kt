package sarankar.app.jetnewsapp.ui.components.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.twotone.List
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import sarankar.app.jetnewsapp.R
import sarankar.app.jetnewsapp.ui.navigation.JetNewsNavigator
import sarankar.app.jetnewsapp.ui.navigation.Screen
import sarankar.app.jetnewsapp.ui.theme.JetNewsAppTheme

@Composable
fun AppDrawer(
    modifier: Modifier = Modifier,
    currentRoute: String,
    navigator: JetNewsNavigator,
    closeDrawer: () -> Unit,
) {
    ModalDrawerSheet {
        JetNewsLogo(
            modifier = Modifier.padding(
                horizontal = 28.dp,
                vertical = 24.dp,
            )
        )
        NavigationDrawerItem(
            label = {
                Text(stringResource(R.string.home))
            },
            icon = {
                Icon(Icons.Rounded.Home, "")
            },
            selected = currentRoute == Screen.Home.route,
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
            onClick = {
                navigator.navigateToHome();
                closeDrawer();
            },
        )
        NavigationDrawerItem(
            label = {
                Text(stringResource(R.string.interests))
            },
            icon = {
                Icon(Icons.Default.ListAlt, "")
            },
            selected = currentRoute == Screen.Interests.route,
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
            onClick = {
                navigator.navigateToInterests();
                closeDrawer();
            },
        )
    }
}

@Preview
@Composable
fun AppDrawerPreview() {
    JetNewsAppTheme {
        AppDrawer(
            currentRoute = Screen.Home.route,
            navigator = JetNewsNavigator(
                navController = rememberNavController(),
            ),
            closeDrawer = {},
        )
    }
}
