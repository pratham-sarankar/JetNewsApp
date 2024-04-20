package sarankar.app.jetnewsapp.data.posts.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import sarankar.app.jetnewsapp.data.posts.PostsRepository
import sarankar.app.jetnewsapp.model.Post
import sarankar.app.jetnewsapp.model.PostsFeed
import sarankar.app.jetnewsapp.utils.addOrRemove

class BlockingFakePostsRepository: PostsRepository {

    private val favorites = MutableStateFlow<Set<String>>(setOf())
    private val postsFeed = MutableStateFlow<PostsFeed?>(null)

    override suspend fun getPost(postId: String?): Result<Post> {
        return withContext(Dispatchers.IO){
            val post = posts.allPosts.find { it.id ==postId }
            if(post==null){
                Result.failure(IllegalArgumentException("Unable to find post"))
            }else{
                Result.success(post)
            }
        }
    }

    override suspend fun getPostsFeed(): Result<PostsFeed> {
        postsFeed.update { posts }
        return Result.success(posts)
    }

    override fun observeFavorites(): Flow<Set<String>> = favorites

    override fun observePostsFeed(): Flow<PostsFeed?> = postsFeed

    override suspend fun toggleFavorite(postId: String) {
        favorites.update { it.addOrRemove(postId) }
    }
}