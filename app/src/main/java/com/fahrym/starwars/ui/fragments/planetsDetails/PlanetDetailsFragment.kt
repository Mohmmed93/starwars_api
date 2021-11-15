package com.fahrym.starwars.ui.fragments.planetsDetails

import com.fahrym.starwars.R
import com.fahrym.starwars.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_planet_details.*

class PlanetDetailsFragment : BaseFragment<Boolean, PlanetsDetailViewModel>() {
    override fun getLayout(): Int = R.layout.fragment_planet_details

    override fun onCreateCompleted() {
        setHasOptionsMenu(true)
        createViewModel(PlanetsDetailViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        bindBundle()
    }

    private fun bindBundle() {
        arguments?.apply {
            PlanetDetailsFragmentArgs.fromBundle(this).apply {
                txtName.text = name
                txtGravity.text = gravity
                texOrbitalPeriod.text = orbitalPeriod
            }
        }
    }

    override fun handleState(state: Boolean) {

    }
}