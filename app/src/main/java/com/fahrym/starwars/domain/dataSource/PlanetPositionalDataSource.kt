package com.fahrym.starwars.domain.dataSource

import androidx.paging.PositionalDataSource
import com.fahrym.starwars.data.source.model.Models
import com.fahrym.starwars.domain.entity.PlanetModel
import com.fahrym.starwars.domain.interactor.GetPlanetUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject


class PlanetPositionalDataSource @Inject constructor(
    private val getPlanetUseCase: GetPlanetUseCase
) : PositionalDataSource<Models.PlanetResponse>(), Disposable {

    private var disposing = false
    override fun isDisposed(): Boolean {
        return disposing
    }

    override fun dispose() {
        disposing = true
        compositeDisposable.clear()
    }

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private fun computeCount(): Int {
        return getPlanetUseCase.execute(PlanetModel()).blockingGet().count
    }

    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<Models.PlanetResponse>
    ) {

        val pageNum = params.startPosition / params.loadSize + 1
        val disposable =
            getPlanetUseCase.execute(PlanetModel(params.loadSize, pageNum)).subscribe({ response ->
                callback.onResult(response.results);
            }, { t: Throwable? ->
                throw (t!!)
            })
        compositeDisposable.add(disposable)

    }

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<Models.PlanetResponse>
    ) {
        val totalCount = computeCount()
        val position = computeInitialLoadPosition(params, totalCount)
        val loadSize = computeInitialLoadSize(params, position, totalCount)

        val pageNum = position / loadSize + 1
        val disposable =
            getPlanetUseCase.execute(PlanetModel(loadSize, pageNum)).subscribe({ response ->
                callback.onResult(response.results, position, response.count)
            }, { t: Throwable? ->
                throw (t!!)
            })
        compositeDisposable.add(disposable)

    }
}