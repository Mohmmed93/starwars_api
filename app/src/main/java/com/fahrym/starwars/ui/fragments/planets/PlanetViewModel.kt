package com.fahrym.starwars.ui.fragments.planets

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.fahrym.starwars.data.source.model.Models
import com.fahrym.starwars.domain.dataSource.PlanetDataSourceFactory
import com.fahrym.starwars.ui.base.BaseViewModel
import javax.inject.Inject

private const val PAGE_SIZE = 10
private const val INITIAL_LOAD_SIZE_HINT = 10

class PlanetViewModel @Inject constructor(
    private val dataSourceFactory: PlanetDataSourceFactory
) : BaseViewModel<PagedList<Models.PlanetResponse>>() {


    init {
        createLiveData()
    }

    fun createLiveData() {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
            .setPageSize(PAGE_SIZE)
            .build()
        this.stateLiveData = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build();
    }

}