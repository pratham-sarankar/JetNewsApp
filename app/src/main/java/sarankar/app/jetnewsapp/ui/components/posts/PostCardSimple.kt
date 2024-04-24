package sarankar.app.jetnewsapp.ui.components.posts

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sarankar.app.jetnewsapp.R
import sarankar.app.jetnewsapp.data.posts.impl.post3
import sarankar.app.jetnewsapp.data.posts.impl.posts
import sarankar.app.jetnewsapp.model.Post
import sarankar.app.jetnewsapp.ui.theme.JetNewsAppTheme

@Composable
fun PostCardSimple(
    post: Post,
    navigateToArticle: (postId: String) -> Unit,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit
) {
    val bookmarkAction = stringResource(if (isFavorite) R.string.unbookmark else R.string.bookmark)
    Row(
        modifier = Modifier.clickable { navigateToArticle(post.id) }.semantics {
            customActions = listOf(
                CustomAccessibilityAction(
                    label = bookmarkAction,
                    action = {
                        onToggleFavorite();
                        true
                    }
                )
            )
        }
    ) {
        Image(
            painter = painterResource(id = post.imageThumbId),
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .size(40.dp)
                .clip(MaterialTheme.shapes.small)
        )
        Column(
            Modifier
                .padding(vertical = 10.dp)
                .weight(1f)
        ) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
            Row() {
                Text(
                    text = stringResource(
                        id = R.string.home_post_min_read,
                        formatArgs = arrayOf(
                            post.metadata.author.name,
                            post.metadata.readTimeMinutes
                        )
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun PostCardSimplePreview() {
    JetNewsAppTheme {
        Surface {
            PostCardSimple(
                post = post3,
                navigateToArticle = {},
                isFavorite = false,
                onToggleFavorite = {},
            )
        }
    }
}