package sarankar.app.jetnewsapp.ui.screens.homescreen.uistate

import sarankar.app.jetnewsapp.model.PostsFeed
import sarankar.app.jetnewsapp.utils.ErrorMessage

data class HomeViewModelState(
    val postsFeed: PostsFeed? = null,
    val selectedPostId: String? = null,
    val isArticleOpen: Boolean = false,
    val favorites: Set<String> = emptySet(),
    val isLoading: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList(),
    val searchInput: String = "",
) {
    fun toUiState(): HomeUiState {
        if (postsFeed == null) {
            return HomeUiState.NoPosts(
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput
            )
        } else {
            return HomeUiState.HasPosts(
                postsFeed = postsFeed,
                selectedPost = postsFeed.allPosts.find { it.id == selectedPostId }
                    ?: postsFeed.highlightedPost,
                isArticleOpen = isArticleOpen,
                favorites = favorites,
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput
            )
        }
    }
}