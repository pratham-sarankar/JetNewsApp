package sarankar.app.jetnewsapp.ui.screens.homescreen.components.posts

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sarankar.app.jetnewsapp.model.Post
import sarankar.app.jetnewsapp.ui.components.posts.PostCardPopular

@Composable
fun PostListPopularSection(
    posts: List<Post>,
    navigateToArticle: (postId: String) -> Unit
) {
    Column {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Popular on Jetnews",
            style = MaterialTheme.typography.titleLarge,
        )
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .height(IntrinsicSize.Max)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (post in posts) {
                PostCardPopular(
                    post = post,
                    navigateToArticle = {
                        navigateToArticle(it)
                    },
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 14.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f),
        )
    }
}