package com.fahrym.starwars.di.app

import com.fahrym.starwars.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module()
abstract class BuilderModule {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity
}