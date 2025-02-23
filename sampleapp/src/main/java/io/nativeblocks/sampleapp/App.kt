package io.nativeblocks.sampleapp

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import io.nativeblocks.core.api.NativeblocksEdition
import io.nativeblocks.core.api.NativeblocksManager
import io.nativeblocks.core.api.provider.logger.INativeLogger
import io.nativeblocks.foundation.FoundationProvider
import io.nativeblocks.wandkit.liveKit
import kotlinx.coroutines.runBlocking
import kotlin.system.exitProcess

private const val NATIVEBLOCKS_API_KEY = ""
private const val NATIVEBLOCKS_API_URL = "https://api.nativeblocks.io/gateway/init"

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler(this.applicationContext))

        NativeblocksManager.initialize(
            applicationContext = this,
            edition = NativeblocksEdition.Cloud(
                endpoint = NATIVEBLOCKS_API_URL,
                apiKey = NATIVEBLOCKS_API_KEY,
                developmentMode = true
            )
            /*
            edition = NativeblocksEdition.Community(
                mapOf(
                    "/login" to "https://nativeblocksjsonschema.nyc3.digitaloceanspaces.com/login.json",
                    "/profile" to "https://nativeblocksjsonschema.nyc3.digitaloceanspaces.com/profile.json",
                ),
            )
            */
        )
        NativeblocksManager.getInstance().setLocalization("EN")
        NativeblocksManager.getInstance().liveKit()
        FoundationProvider.provide()
        NativeblocksManager.getInstance().provideEventLogger("LOGGER", AppLogger())
    }

    override fun onTerminate() {
        super.onTerminate()
        NativeblocksManager.getInstance().destroy()
    }
}

class ExceptionHandler(private val context: Context) : Thread.UncaughtExceptionHandler {
    override fun uncaughtException(t: Thread, e: Throwable) {
        Log.d("DEBUG STACKTRACE", e.stackTraceToString())
        runBlocking {
            NativeblocksManager.getInstance().clearAllFrames()
            Toast.makeText(context, e.message.orEmpty(), Toast.LENGTH_LONG).show()
            exitProcess(1)
        }
    }
}

class AppLogger : INativeLogger {
    override fun log(eventName: String, parameters: Map<String, String>) {
        Log.d(eventName, "log: $parameters")
    }
}
