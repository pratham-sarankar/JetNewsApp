package sarankar.app.jetnewsapp.data.posts

import kotlinx.coroutines.flow.Flow
import sarankar.app.jetnewsapp.model.Post
import sarankar.app.jetnewsapp.model.PostsFeed

interface PostsRepository {
    suspend fun getPost(postId: String?): Result<Post>
    suspend fun getPostsFeed(): Result<PostsFeed>
    fun observeFavorites(): Flow<Set<String>>
    fun observePostsFeed(): Flow<PostsFeed?>
    suspend fun toggleFavorite(postId: String)
}
