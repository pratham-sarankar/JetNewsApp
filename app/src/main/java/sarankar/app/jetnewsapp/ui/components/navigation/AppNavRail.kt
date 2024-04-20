package sarankar.app.jetnewsapp.ui.components.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import sarankar.app.jetnewsapp.R
import sarankar.app.jetnewsapp.ui.navigation.JetNewsNavigator
import sarankar.app.jetnewsapp.ui.navigation.Screen

@Composable
fun AppNavRail(
    modifier: Modifier = Modifier,
    currentRoute: String,
    navigator: JetNewsNavigator,
) {
    NavigationRail(
        modifier = modifier,
        header = {
            Icon(
                painter = painterResource(id = R.drawable.ic_jetnews_logo),
                contentDescription = null,
                Modifier.padding(vertical = 12.dp),
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    ) {
        Spacer(modifier = Modifier.weight(1f))
        NavigationRailItem(
            selected = currentRoute == Screen.Home.route,
            onClick = {
                navigator.navigateToHome()
            },
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Home,
                    contentDescription = stringResource(id = R.string.home)
                )
            },
            label = {
                Text(text = stringResource(id = R.string.home))
            },
            alwaysShowLabel = false,
        )
        NavigationRailItem(
            selected = currentRoute == Screen.Interests.route,
            onClick = {
                navigator.navigateToInterests()
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.ListAlt,
                    contentDescription = stringResource(id = R.string.interests)
                )
            },
            label = {
                Text(text = stringResource(id = R.string.interests))
            },
            alwaysShowLabel = false,
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}