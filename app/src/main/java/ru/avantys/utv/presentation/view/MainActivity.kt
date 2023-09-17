package ru.avantys.utv.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.avantys.utv.databinding.ActivityMainBinding
import ru.avantys.utv.presentation.MainAdapter
import ru.avantys.utv.presentation.viewModel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()
    private val adapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureRecycleView()
        subscribeToStories()
    }

    private fun configureRecycleView() {
        binding.recyclerView.adapter = adapter
    }

    private fun subscribeToStories() {
        lifecycleScope.launch {
            viewModel.stories.collect {
                adapter.setStoriesList(stories = it)
            }
        }
    }
}
