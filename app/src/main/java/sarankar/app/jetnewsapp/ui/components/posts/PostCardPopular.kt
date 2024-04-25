package sarankar.app.jetnewsapp.ui.components.posts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import sarankar.app.jetnewsapp.R
import sarankar.app.jetnewsapp.model.Post

@Composable
fun PostCardPopular(
    post: Post,
    navigateToArticle: (postId: String) -> Unit
) {
    Card(
        onClick = { navigateToArticle(post.id) },
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.width(280.dp),
    ) {
        Column {
            Image(
                painter = painterResource(id = post.imageId),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            )

            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                Text(
                    post.title, style = MaterialTheme.typography.headlineSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    post.metadata.author.name,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = stringResource(
                        id = R.string.home_post_min_read,
                        formatArgs = arrayOf(
                            post.metadata.date,
                            post.metadata.readTimeMinutes
                        ),
                    ),
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}