package com.fahrym.starwars.di.viewmodel

import androidx.lifecycle.ViewModel
import com.fahrym.starwars.ui.fragments.planets.PlanetViewModel
import com.fahrym.starwars.ui.fragments.planetsDetails.PlanetsDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(clazz = PlanetViewModel::class)
    abstract fun bindPlanetViewModel(viewModel: PlanetViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(clazz = PlanetsDetailViewModel::class)
    abstract fun bindPlanetsDetailViewModel(viewModel: PlanetsDetailViewModel): ViewModel

}