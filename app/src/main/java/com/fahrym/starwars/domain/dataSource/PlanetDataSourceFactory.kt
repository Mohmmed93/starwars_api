package com.fahrym.starwars.domain.dataSource

import androidx.paging.DataSource
import com.fahrym.starwars.data.source.model.Models
import javax.inject.Inject

class PlanetDataSourceFactory @Inject constructor(
    private val dataSource: PlanetPositionalDataSource
) : DataSource.Factory<Int, Models.PlanetResponse>() {

    override fun create(): DataSource<Int, Models.PlanetResponse> {
        return dataSource
    }
}