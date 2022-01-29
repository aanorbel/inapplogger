package com.velmurugan.inapploggerexample

import android.app.Application
import com.velmurugan.inapplogger.InAppLogger

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        InAppLogger.initialize(this).apply {
            deleteOldLog()
        }

    }
}