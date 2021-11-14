package com.fahrym.starwars.di.app

import android.app.Activity
import com.fahrym.starwars.MainApplication
import com.fahrym.starwars.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        AndroidSupportInjectionModule::class,
        BuilderModule::class, ViewModelModule::class,
        DataModule::class
    ]
)
interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MainApplication): Builder

        fun build(): AppComponent

    }

    val activityInjector: DispatchingAndroidInjector<Activity>
}