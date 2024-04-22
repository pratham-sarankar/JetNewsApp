package sarankar.app.jetnewsapp.ui.screens.homescreen.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import sarankar.app.jetnewsapp.R
import sarankar.app.jetnewsapp.data.posts.PostsRepository
import sarankar.app.jetnewsapp.model.PostsFeed
import sarankar.app.jetnewsapp.ui.screens.homescreen.uistate.HomeViewModelState
import sarankar.app.jetnewsapp.utils.ErrorMessage
import java.util.UUID

class HomeViewModel(
    private val postsRepository: PostsRepository,
    preSelectedPostId: String?,
) : ViewModel() {
    private val viewModelState = MutableStateFlow(
        HomeViewModelState(
            isLoading = true,
            selectedPostId = preSelectedPostId,
            isArticleOpen = preSelectedPostId != null
        )
    )

    val uiState = viewModelState
        .map(HomeViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        refreshPosts()

        viewModelScope.launch {
            postsRepository.observeFavorites().collect { favorites ->
                viewModelState.update { it.copy(favorites = favorites) }
            }
        }
    }

    fun refreshPosts() {
        viewModelState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result: Result<PostsFeed> = postsRepository.getPostsFeed()
            viewModelState.update {
                if (result.isSuccess) {
                    it.copy(postsFeed = result.getOrNull(), isLoading = false)
                } else {
                    val errorMessage = it.errorMessages + ErrorMessage(
                        id = UUID.randomUUID().mostSignificantBits,
                        messageId = R.string.load_error
                    )
                    it.copy(
                        isLoading = false,
                        errorMessages = errorMessage,
                    )
                }
            }
        }
    }

    fun toggleFavorite(postId: String){
        viewModelScope.launch {
            postsRepository.toggleFavorite(postId)
        }
    }

    fun selectArticle(postId: String){
        interactedWithArticleDetails(postId)
    }

    fun errorShown(errorId: Long) {
        viewModelState.update { currentUiState ->
            val errorMessages = currentUiState.errorMessages.filterNot { it.id == errorId }
            currentUiState.copy(errorMessages = errorMessages)
        }
    }

    fun interactedWithFeed() {
        viewModelState.update {
            it.copy(isArticleOpen = false)
        }
    }


    private fun interactedWithArticleDetails(postId: String) {
        viewModelState.update {
            it.copy(
                selectedPostId = postId,
                isArticleOpen = true
            )
        }
    }

    fun onSearchInputChanged(searchInput: String) {
        viewModelState.update {
            it.copy(searchInput = searchInput)
        }
    }


    companion object {
        fun provideFactory(
            postsRepository: PostsRepository,
            preSelectedPostId: String? = null,
        ):ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(
                    postsRepository = postsRepository,
                    preSelectedPostId = preSelectedPostId
                ) as T
            }
        }
    }

}