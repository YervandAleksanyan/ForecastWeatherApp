package com.weatherapi.core.locationprovider

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.*
import com.google.android.gms.tasks.CancellationTokenSource

@SuppressLint("MissingPermission")
class LocationProviderClient(context: Context) {

    var listener: LocationListener? = null
    private val fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

     fun getCurrentLocation() {
        val cancellationToken = CancellationTokenSource()
        fusedLocationProviderClient
            .getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, cancellationToken.token)
            .addOnCompleteListener {
                if (it.result != null) {
                    listener?.onLocationResult(it.result)
                }
            }
    }
}