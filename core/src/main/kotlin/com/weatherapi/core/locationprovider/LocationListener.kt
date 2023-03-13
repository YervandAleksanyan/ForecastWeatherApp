package com.weatherapi.core.locationprovider

import android.location.Location

interface LocationListener {

    fun onLocationResult(locationList: Location)

}