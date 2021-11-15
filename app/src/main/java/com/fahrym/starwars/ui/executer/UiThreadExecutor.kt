package com.fahrym.starwars.ui.executer

import com.fahrym.starwars.domain.executer.PostExecutionThread
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler


/**
 * provides Ui thread for [io.reactivex]
 */

class UiThreadExecutor : PostExecutionThread {
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}