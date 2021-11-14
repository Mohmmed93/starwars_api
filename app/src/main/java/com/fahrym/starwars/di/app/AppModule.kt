package com.fahrym.starwars.di.app

import android.content.Context
import com.fahrym.starwars.MainApplication
import com.fahrym.starwars.data.extractor.NetworkJobExecutor
import com.fahrym.starwars.di.scope.ForApplication
import com.fahrym.starwars.domain.executer.PostExecutionThread
import com.fahrym.starwars.domain.executer.UseCaseExecutor
import com.fahrym.starwars.domain.repository.ConnectivityManager
import com.fahrym.starwars.ui.executer.UiThreadExecutor
import com.fahrym.starwars.util.ConnectivityManagerImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideUseCaseExecutor(): UseCaseExecutor {
        return NetworkJobExecutor()
    }

    @Provides
    @JvmStatic
    @Singleton
    fun postExecutionThread(): PostExecutionThread = UiThreadExecutor()


    @Provides
    @JvmStatic
    @ForApplication
    fun provideContext(app: MainApplication): Context = app.applicationContext

    @Provides
    @JvmStatic
    @Singleton
    fun provideConnectivityManager(connectivityManagerImp: ConnectivityManagerImp)
            : ConnectivityManager = connectivityManagerImp
}
