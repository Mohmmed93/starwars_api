package com.fahrym.starwars.domain.interactor.base

import com.fahrym.starwars.domain.executer.PostExecutionThread
import com.fahrym.starwars.domain.executer.UseCaseExecutor
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single


/**
 * Each [UseCase] of the system orchestrate the flow of data to and from the entities.
 * Outer layers of system can execute use cases by calling [execute]} method.
 * ALso you can use [useCaseExecutor] to execute the job in a background thread and
 * [postExecutionThread] to post the result to another thread(UI thread).
 * @param Responses response type for use case.
 * @param Params input parameters for use case
 */
abstract class UseCase<Responses, Params>(
    protected val useCaseExecutor: UseCaseExecutor,
    protected val postExecutionThread: PostExecutionThread
) {
    private fun getUseCaseExecutor(): Scheduler {
        return useCaseExecutor.scheduler
    }

    private fun getPostExecutionThread(): Scheduler {
        return postExecutionThread.scheduler
    }

    fun <T> Single<T>.applySchedulers(): Single<T> {
        return subscribeOn(getUseCaseExecutor()).observeOn(getPostExecutionThread())
    }
}