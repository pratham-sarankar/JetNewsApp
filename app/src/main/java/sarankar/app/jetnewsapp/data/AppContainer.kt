package sarankar.app.jetnewsapp.data

import android.content.Context
import sarankar.app.jetnewsapp.data.posts.PostsRepository
import sarankar.app.jetnewsapp.data.posts.impl.FakePostsRepository


interface AppContainer{
    val postsRepository: PostsRepository
}

class AppContainerImpl(
    private val applicationContext: Context
) : AppContainer {
    override val postsRepository: PostsRepository by lazy {
        FakePostsRepository()
    }
}