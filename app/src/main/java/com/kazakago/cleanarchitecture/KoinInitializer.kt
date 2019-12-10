package com.kazakago.cleanarchitecture

import android.app.Application
import com.kazakago.cleanarchitecture.repository.di.dataModule
import com.kazakago.cleanarchitecture.usecase.di.domainModule
import com.kazakago.cleanarchitecture.viewmodel.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

fun Application.initializeKoin() {
    startKoin {
        androidLogger()
        androidContext(this@initializeKoin)
        modules(
            listOf(
                presentationModule,
                domainModule,
                dataModule
            )
        )
    }
}
