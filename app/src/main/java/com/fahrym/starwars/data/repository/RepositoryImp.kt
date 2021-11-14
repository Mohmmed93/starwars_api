package com.fahrym.starwars.data.repository

import com.fahrym.starwars.data.source.model.Models
import com.fahrym.starwars.data.source.remote.APIService
import com.fahrym.starwars.domain.entity.PlanetModel
import com.fahrym.starwars.domain.repository.Repository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val restApi: APIService) : Repository {
    override fun getPlanetUseCase(param: PlanetModel): Single<Models.BasePlanet> {
        return Single.create { emitter ->
            restApi.getPlanets(param.pageNum).subscribe({ response ->
                emitter.onSuccess(response)
            }, {
                emitter.onError(it)
            })
        }
    }
}
