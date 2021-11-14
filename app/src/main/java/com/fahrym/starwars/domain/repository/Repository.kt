package com.fahrym.starwars.domain.repository

import com.fahrym.starwars.data.source.model.Models
import com.fahrym.starwars.domain.entity.PlanetModel
import io.reactivex.rxjava3.core.Single

interface Repository {
    fun getPlanetUseCase(param: PlanetModel): Single<Models.BasePlanet>
}