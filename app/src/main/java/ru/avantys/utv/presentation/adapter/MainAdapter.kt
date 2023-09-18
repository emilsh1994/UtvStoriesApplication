package ru.avantys.utv.presentation.adapter

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.avantys.utv.R
import ru.avantys.utv.databinding.StoryItemBinding
import ru.avantys.utv.domain.model.Story

class MainAdapter(
    private inline val storyRootClick: (String) -> Unit,
    private inline val storyIconClick: (Story) -> Unit
) : ListAdapter<Story, MainAdapter.MainViewHolder>(StoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StoryItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MainViewHolder(private val binding: StoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(story: Story) {
            binding.tvStoryName.text = story.name
            Glide.with(binding.root.context)
                .load(story.logoUrl)
                .transform(CenterInside(), RoundedCorners(8))
                .into(binding.storyImage)

            if (story.isFavourite) {
                binding.fav.setImageResource(R.drawable.favorite_filled)
                binding.fav.setColorFilter(
                    binding.root.context.getColor(R.color.fav_filled),
                    PorterDuff.Mode.MULTIPLY
                )
            } else {
                binding.fav.setImageResource(R.drawable.favorite_outline)
                binding.fav.setColorFilter(
                    binding.root.context.getColor(R.color.fav_outline),
                    PorterDuff.Mode.MULTIPLY
                )
            }

            binding.fav.setOnClickListener {
                storyIconClick.invoke(story)
            }

            binding.storyImage.setOnClickListener {
                storyRootClick.invoke(story.url)
            }
        }
    }
}
