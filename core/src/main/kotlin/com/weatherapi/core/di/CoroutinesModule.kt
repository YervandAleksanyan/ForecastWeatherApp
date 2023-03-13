package com.weatherapi.core.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DispatchersName {
    const val IO = "DispatcherIO"
    const val Main = "DispatcherMain"
    const val Default = "DispatcherDefault"
    const val Immediate = "DispatcherImmediate"
}

val coroutineModule = module {

    single(named(DispatchersName.IO)) { Dispatchers.IO }

    single(named(DispatchersName.Main)) { Dispatchers.Main }

    single(named(DispatchersName.Default)) { Dispatchers.Default }

    single(named(DispatchersName.Immediate)) { Dispatchers.Main.immediate }

    /**
     * External Scope - for long running operations
     */
    single {
        val dispatcher: CoroutineDispatcher = get(named(DispatchersName.IO))
        CoroutineScope(dispatcher + SupervisorJob())
    }
}