package ru.avantys.utv.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.avantys.utv.domain.model.Story

class StoryDiffCallback : DiffUtil.ItemCallback<Story>() {

    override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
        return oldItem == newItem
    }

}