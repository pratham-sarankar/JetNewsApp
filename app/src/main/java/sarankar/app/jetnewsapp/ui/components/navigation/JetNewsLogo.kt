package sarankar.app.jetnewsapp.ui.components.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import sarankar.app.jetnewsapp.R

@Composable
fun JetNewsLogo(
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Icon(
            painter = painterResource(id = R.drawable.ic_jetnews_logo),
            contentDescription = stringResource(R.string.jetnews_logo),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_jetnews_wordmark),
            contentDescription = stringResource(R.string.jetnews_wordmark),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}