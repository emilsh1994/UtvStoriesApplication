package ru.avantys.utv.presentation.adapter

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.avantys.utv.R
import ru.avantys.utv.databinding.RecyclerviewItemBinding
import ru.avantys.utv.domain.model.Story

class MainAdapter(
    private var storyList: List<Story>,
    private inline val storyRootClick: String.() -> Unit,
    private inline val storyIconClick: Story.() -> Unit
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>(), Filterable {

    private var storyFilterList: List<Story>

    init {
        storyFilterList = storyList
    }

    inner class MainViewHolder(private val binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(story: Story) {
            binding.tvStoryName.text = story.name
            Glide.with(binding.root.context)
                .load(story.logoUrl)
                .transform(CenterInside(), RoundedCorners(8))
                .into(binding.storyImage)

            if (story.isFavourite) {
                binding.fav.setImageResource(R.drawable.favorite_filled)
                binding.fav.setColorFilter(binding.root.context.getColor(R.color.fav_filled), PorterDuff.Mode.MULTIPLY)
            } else {
                binding.fav.setImageResource(R.drawable.favorite_outline)
                binding.fav.setColorFilter(binding.root.context.getColor(R.color.fav_outline), PorterDuff.Mode.MULTIPLY)
            }

            binding.root.setOnClickListener {
                storyIconClick.invoke(story)
            }

            binding.storyImage.setOnClickListener {
                storyRootClick.invoke(story.url)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return storyFilterList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(storyFilterList[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                storyFilterList = if (charSearch.isEmpty()) {
                    storyList
                } else {
                    storyList.filter {
                        it.name.lowercase().contains(charSearch.lowercase())
                    }
                }
                val result = FilterResults()
                result.values = storyFilterList
                return result
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                storyFilterList = results?.values as MutableList<Story>
                notifyDataSetChanged()
            }
        }
    }
}

