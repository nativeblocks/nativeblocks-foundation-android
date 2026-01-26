package io.nativeblocks.sampleapp

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import io.nativeblocks.core.api.NativeblocksEdition
import io.nativeblocks.core.api.NativeblocksManager
import io.nativeblocks.core.api.provider.logger.INativeLogger
import io.nativeblocks.core.api.provider.logger.LoggerEventLevel
import io.nativeblocks.foundation.FoundationProvider
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
        FoundationProvider.provide()
        NativeblocksManager.getInstance().provideEventLogger("LOGGER", AppLogger("default"))
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

private class AppLogger(val name: String) : INativeLogger {
    override fun log(
        level: LoggerEventLevel,
        event: String,
        message: String,
        parameters: Map<String, String>,
    ) {
        val paramsText = if (parameters.isNotEmpty()) {
            parameters.entries.joinToString("\n") { "║   ${it.key}: ${it.value}" }
        } else {
            "║   (none)"
        }
        val logString = """
            |╔═══════════════════════════════════════════════════════════
            |║ Nativeblocks Log
            |╠═══════════════════════════════════════════════════════════
            |║ Instance: $name
            |║ Level   : ${level.name}
            |║ Event   : $event
            |║ Message : 
            |║   $message
            |║ Parameters:
            |$paramsText
            |╚═══════════════════════════════════════════════════════════
        """.trimMargin()

        Log.d("Nativeblocks_$name", logString)
    }
}