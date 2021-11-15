package com.fahrym.starwars.domain.repository

interface ConnectivityManager {
    fun hasNetwork(): Boolean?
}