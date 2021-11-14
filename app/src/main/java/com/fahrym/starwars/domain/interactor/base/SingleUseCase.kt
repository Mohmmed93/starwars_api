package com.fahrym.starwars.domain.interactor.base

import com.fahrym.starwars.domain.executer.PostExecutionThread
import com.fahrym.starwars.domain.executer.UseCaseExecutor
import com.fahrym.starwars.domain.repository.Repository
import io.reactivex.rxjava3.core.Single

/**
 * @param Responses The response value emitted by the Observable.
 * @param Params The request value.
 */
abstract class SingleUseCase<Responses, Params>(
    useCaseExecutor: UseCaseExecutor,
    postExecutionThread: PostExecutionThread,
    protected var repository: Repository
) :
    UseCase<Single<Responses>, Params>(useCaseExecutor, postExecutionThread) {

    open fun execute(params: Params): Single<Responses> {
        return interact(params).applySchedulers()
    }

    protected abstract fun interact(params: Params): Single<Responses>

}