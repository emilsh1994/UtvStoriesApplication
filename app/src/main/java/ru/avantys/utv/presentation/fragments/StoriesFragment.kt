package ru.avantys.utv.presentation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import ru.avantys.utv.databinding.FragmentStoriesBinding
import ru.avantys.utv.domain.model.Story
import ru.avantys.utv.presentation.adapter.MainAdapter
import ru.avantys.utv.presentation.viewModel.StoriesViewModel

@AndroidEntryPoint
class StoriesFragment : Fragment() {

    private var _binding: FragmentStoriesBinding? = null
    private val binding: FragmentStoriesBinding
        get() = _binding!!

    private val viewModel by viewModels<StoriesViewModel>()
    private val adapter = MainAdapter(::storyRootClick, ::storyIconClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeStoriesList()
        observeSearchText()
        setTextChangedListener()
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter
    }

    private fun observeStoriesList() {
        lifecycleScope.launch {
            combine(
                viewModel.stories,
                viewModel.searchText
            ) { stories, searchText ->
                if (searchText.isBlank()) {
                    updateRecyclerView(stories)
                } else {
                    updateRecyclerView(stories.filter { story: Story ->
                        story.name.lowercase().contains(searchText.lowercase())
                    })
                }
            }.collect()
        }
    }

    private fun observeSearchText() {
        lifecycleScope.launch {
            viewModel.searchText.collect {
                if (binding.textInputEditTextSearch.text.toString() != it) {
                    binding.textInputEditTextSearch.setText(it)
                }
            }
        }
    }

    private fun updateRecyclerView(stories: List<Story>) {
        adapter.submitList(stories)
    }

    private fun setTextChangedListener() {
        binding.textInputEditTextSearch.doAfterTextChanged {
            viewModel.setSearchText(it?.toString() ?: "")
        }
    }

    private fun storyRootClick(storyUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(storyUrl)
        startActivity(intent)
    }

    private fun storyIconClick(story: Story) {
        viewModel.setFavouriteStatus(story)
    }
}