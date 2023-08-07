package com.test.compose

import android.app.Application
import com.test.compose.di.DIManager
import com.o3interfaces.fusioninspect.core.FusionInspect

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DIManager.initialize(this)

        // Fusion future demo app
        val clientSecret = "3fe5b8aacb124bf78c4631938166813f"
        val projectToken = "CAD32DEC08E34BE492B8C79FC3BF90E0"

        FusionInspect.Builder(
            this,
            clientSecret,
            projectToken
        ).build()
    }
}