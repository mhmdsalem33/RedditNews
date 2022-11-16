package com.example.redditnews.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext


object NetworkStatus {
    @Suppress("deprecation")
    @JvmStatic
    fun isNetworkAvailable( @ApplicationContext  context: Context ): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true

                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            return connectivityManager.activeNetworkInfo?.isConnected ?: false
        }

    }
}