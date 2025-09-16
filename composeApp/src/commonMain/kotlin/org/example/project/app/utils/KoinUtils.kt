package org.example.project.app.utils

import org.example.project.di.commonModule
import org.koin.core.context.startKoin

object KoinUtils {
    fun startKoin() {
        startKoin {
            modules(commonModule)
        }
    }
}