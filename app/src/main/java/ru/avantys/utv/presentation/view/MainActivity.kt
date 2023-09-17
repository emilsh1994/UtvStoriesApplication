package ru.avantys.utv.presentation.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.avantys.utv.databinding.ActivityMainBinding
import ru.avantys.utv.domain.model.Story
import ru.avantys.utv.presentation.adapter.MainAdapter
import ru.avantys.utv.presentation.viewModel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textInputEditTextSearch.doAfterTextChanged {
            adapter.filter.filter(it.toString())
        }

        observeStoriesList()
    }

    private fun observeStoriesList() {
        lifecycleScope.launch {
            viewModel.stories.collect { stories ->
                updateRecyclerView(stories)
            }
        }
    }

    private fun updateRecyclerView(stories: List<Story>) {
        adapter = MainAdapter(stories, ::storyRootClick, ::storyIconClick)
        binding.recyclerView.adapter = adapter
    }

    private fun storyRootClick(storyUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(storyUrl)
        startActivity(intent)
    }

    private fun storyIconClick(story: Story) {
        Toast.makeText(this, story.name, Toast.LENGTH_SHORT).show()
        viewModel.setFavouriteStatus(story)
    }
}
