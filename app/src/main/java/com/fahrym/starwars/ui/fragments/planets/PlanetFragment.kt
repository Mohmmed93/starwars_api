package com.fahrym.starwars.ui.fragments.planets

import androidx.navigation.Navigation.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahrym.starwars.R
import com.fahrym.starwars.data.source.model.Models
import com.fahrym.starwars.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_planet.*


class PlanetFragment : BaseFragment<PagedList<Models.PlanetResponse>, PlanetViewModel>() {

    private val clickListener: ClickListener = this::onPhotoClicked
    private val planetListAdapter = PlanetAdapter(clickListener)

    override fun onCreateCompleted() {
        initRecyclerView()
        createViewModel(PlanetViewModel::class.java)
    }

    private fun onPhotoClicked(planet: Models.PlanetResponse) {
        view?.let {
            findNavController(it).navigate(
                PlanetFragmentDirections.actionPlanetFragmentToPlanetDetailsFragment(
                    planet.name,
                    planet.orbital_period,
                    planet.gravity
                )
            )
        }
    }

    private fun initRecyclerView() {
        rv_planet.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = planetListAdapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_planet
    }

    private fun render(pagedPlanetList: PagedList<Models.PlanetResponse>) {
        planetListAdapter.submitList(pagedPlanetList)
    }

    override fun handleState(state: PagedList<Models.PlanetResponse>) {
        render(state)
    }
}