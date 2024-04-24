package sarankar.app.jetnewsapp.ui.screens.homescreen.components.posts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import sarankar.app.jetnewsapp.R
import sarankar.app.jetnewsapp.model.Post
import sarankar.app.jetnewsapp.ui.components.posts.PostCardTop

@Composable
fun PostListTopSection(post: Post, navigateToArticle: (postId: String) -> Unit) {
    Text(
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
        text = stringResource(id = R.string.home_top_section_title),
        style = MaterialTheme.typography.titleMedium
    )
    PostCardTop(post = post, modifier = Modifier.clickable {
        navigateToArticle(post.id)
    })
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
    )
}