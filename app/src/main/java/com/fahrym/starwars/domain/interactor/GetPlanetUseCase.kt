package com.fahrym.starwars.domain.interactor

import com.fahrym.starwars.data.source.model.Models
import com.fahrym.starwars.domain.entity.PlanetModel
import com.fahrym.starwars.domain.executer.PostExecutionThread
import com.fahrym.starwars.domain.executer.UseCaseExecutor
import com.fahrym.starwars.domain.interactor.base.SingleUseCase
import com.fahrym.starwars.domain.repository.Repository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetPlanetUseCase @Inject
constructor(
    useCaseExecutor: UseCaseExecutor,
    postExecutionThread: PostExecutionThread,
    repository: Repository
) : SingleUseCase<Models.BasePlanet, PlanetModel>(
    useCaseExecutor,
    postExecutionThread,
    repository
) {
    override fun interact(params: PlanetModel): Single<Models.BasePlanet> {
        return repository.getPlanetUseCase(params)
    }
}