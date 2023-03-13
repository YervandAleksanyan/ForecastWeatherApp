package com.weatherapi.data.di

import com.weatherapi.data.BuildConfig
import com.weatherapi.data.api.BaseInterceptor
import com.weatherapi.data.api.NetworkLogInterceptor
import com.weatherapi.data.api.WeatherAppRestServiceApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

internal val networkModule = module {
    single { getOkHttpClient(baseInterceptor = get(), networkLogInterceptor = get()) }
    single { getRetrofit(okHttpClient = get()) }
    single { getWeatherAppRestService(retrofit = get()) }
    single { BaseInterceptor() }
    single { NetworkLogInterceptor() }

}

private fun getRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit
    .Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .build()

private fun getWeatherAppRestService(retrofit: Retrofit): WeatherAppRestServiceApi =
    retrofit.create(WeatherAppRestServiceApi::class.java)

private fun getOkHttpClient(
    baseInterceptor: BaseInterceptor,
    networkLogInterceptor: NetworkLogInterceptor,
): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(baseInterceptor)
    .addInterceptor(networkLogInterceptor)
    .build()

