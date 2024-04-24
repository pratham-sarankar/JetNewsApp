package sarankar.app.jetnewsapp.ui.components.posts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sarankar.app.jetnewsapp.R
import sarankar.app.jetnewsapp.data.posts.impl.posts
import sarankar.app.jetnewsapp.model.Post
import sarankar.app.jetnewsapp.ui.theme.JetNewsAppTheme

@Composable
fun PostCardTop(post: Post, modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = post.imageId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .heightIn(min = 180.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = post.title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = post.metadata.author.name,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(bottom=4.dp)
        )
        Text(
            text = stringResource(
                id = R.string.home_post_min_read,
                formatArgs = arrayOf(
                    post.metadata.date,
                    post.metadata.readTimeMinutes
                )
            ),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview
@Composable
private fun PostCardTopPreview() {
    JetNewsAppTheme {
        Surface {
            PostCardTop(post = posts.highlightedPost)
        }
    }
}