package com.fahrym.starwars.ui.fragments.planets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fahrym.starwars.R
import com.fahrym.starwars.data.source.model.Models
import kotlinx.android.synthetic.main.item_planet.view.*

class PlanetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bind(planet: Models.PlanetResponse?) {
        planet?.apply {
            itemView.apply {
                txtPlanetName.text = name
                txtClimate.text = climate
                Glide.with(ivPicsumPlanet.context)
                    .load("https://picsum.photos/id/${layoutPosition}/50/50")
                    /*.apply(
                        RequestOptions()
                            .placeholder(R.drawable.imag_placeholder)
                            .error(R.drawable.img_error)
                    )*/
                    .into(ivPicsumPlanet)
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): PlanetViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                .inflate(R.layout.item_planet, parent, false)

            return PlanetViewHolder(view)
        }
    }
}