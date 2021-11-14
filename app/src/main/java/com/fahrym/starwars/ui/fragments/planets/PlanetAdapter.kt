package com.fahrym.starwars.ui.fragments.planets

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.fahrym.starwars.data.source.model.Models

typealias ClickListener = (Models.PlanetResponse) -> Unit

class PlanetAdapter(private val clickListener: ClickListener) :
    PagedListAdapter<Models.PlanetResponse, PlanetViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        val photo = getItem(position)

        with(holder) {
            bind(photo)
            photo?.let {
                itemView.setOnClickListener {
                    clickListener(photo)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder =
        PlanetViewHolder.from(parent)

    companion object {
        /**
         * This diff callback informs the PagedListAdapter how to compute list differences when new
         * PagedLists arrive.
         */
        private val diffCallback = object : DiffUtil.ItemCallback<Models.PlanetResponse>() {
            override fun areItemsTheSame(
                oldItem: Models.PlanetResponse,
                newItem: Models.PlanetResponse
            ): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: Models.PlanetResponse,
                newItem: Models.PlanetResponse
            ): Boolean =
                oldItem == newItem
        }
    }
}