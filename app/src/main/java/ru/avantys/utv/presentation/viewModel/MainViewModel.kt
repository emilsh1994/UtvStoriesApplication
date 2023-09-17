package ru.avantys.utv.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.avantys.utv.domain.model.Story
import ru.avantys.utv.domain.usecase.GetStoriesUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getStories: GetStoriesUseCase
): ViewModel() {

    private val _stories = MutableStateFlow(emptyList<Story>())
    val stories = _stories.asStateFlow()

    init {
        fetchStories()
    }

    private fun fetchStories() {
        viewModelScope.launch {
            _stories.emit(getStories.invoke())
        }
    }

}