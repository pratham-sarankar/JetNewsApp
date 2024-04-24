package sarankar.app.jetnewsapp.ui.screens.homescreen.components.posts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sarankar.app.jetnewsapp.model.Post
import sarankar.app.jetnewsapp.ui.components.posts.PostCardSimple

@Composable
fun PostListSimpleSection(
    posts: List<Post>,
    navigateToArticle: (postId: String) -> Unit,
    favorites: Set<String>,
    onToggleFavorite: (postId: String) -> Unit
) {
    Column {
        posts.forEach { post ->
            PostCardSimple(
                post = post,
                isFavorite = favorites.contains(post.id),
                onToggleFavorite = {
                    onToggleFavorite(post.id)
                },
                navigateToArticle = {
                    navigateToArticle(post.id)
                },
            )
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 14.dp),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
            )
        }
    }
}