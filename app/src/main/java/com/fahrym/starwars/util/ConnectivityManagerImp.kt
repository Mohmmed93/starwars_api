package com.fahrym.starwars.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.fahrym.starwars.di.scope.ForApplication
import javax.inject.Inject

class ConnectivityManagerImp @Inject constructor(@ForApplication val context: Context) :
    com.fahrym.starwars.domain.repository.ConnectivityManager {
    override fun hasNetwork(): Boolean? {
        var isConnected: Boolean? = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }
}