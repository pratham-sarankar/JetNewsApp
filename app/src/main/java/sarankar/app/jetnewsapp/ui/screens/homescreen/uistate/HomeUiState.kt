package sarankar.app.jetnewsapp.ui.screens.homescreen.uistate

import sarankar.app.jetnewsapp.model.Post
import sarankar.app.jetnewsapp.model.PostsFeed
import sarankar.app.jetnewsapp.utils.ErrorMessage

sealed interface HomeUiState {
    val isLoading: Boolean
    val errorMessages: List<ErrorMessage>
    val searchInput: String

    data class NoPosts(
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
        override val searchInput: String
    ): HomeUiState

    data class HasPosts(
        val postsFeed: PostsFeed,
        val selectedPost: Post,
        val isArticleOpen: Boolean,
        val favorites: Set<String>,
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
        override val searchInput: String
    ): HomeUiState
}

