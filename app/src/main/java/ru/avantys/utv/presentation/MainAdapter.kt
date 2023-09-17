package ru.avantys.utv.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.avantys.utv.databinding.RecyclerviewItemBinding
import ru.avantys.utv.domain.model.Story

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private var stories = mutableListOf<Story>()

    fun setStoriesList(stories: List<Story>) {
        this.stories = stories.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return stories.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(stories[position])
    }
}

class MainViewHolder(private val binding: RecyclerviewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(story: Story) {
        binding.tvStoryName.text = story.name
        binding.tvStoryUrl.text = story.logoUrl
    }
}
