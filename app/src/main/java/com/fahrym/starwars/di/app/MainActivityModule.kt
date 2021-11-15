package com.fahrym.starwars.di.app

import com.fahrym.starwars.ui.fragments.planets.PlanetFragment
import com.fahrym.starwars.ui.fragments.planetsDetails.PlanetDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun contributePlanetFragment(): PlanetFragment

    @ContributesAndroidInjector
    abstract fun contributePlanetDetailsFragment(): PlanetDetailsFragment
}