package org.example.project

import android.app.Application
import org.example.project.di.commonModule
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(commonModule)
        }
    }
}