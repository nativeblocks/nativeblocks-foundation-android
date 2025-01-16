package io.nativeblocks.sampleapp

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import io.nativeblocks.core.api.NativeblocksEdition
import io.nativeblocks.core.api.NativeblocksManager
import io.nativeblocks.foundation.FoundationTypeProvider
import io.nativeblocks.foundation.integration.consumer.block.FoundationBlockProvider
import io.nativeblocks.wandkit.liveKit
import kotlinx.coroutines.runBlocking
import kotlin.system.exitProcess

private const val NATIVEBLOCKS_API_KEY = ""
private const val NATIVEBLOCKS_API_URL = "https://edge.api.nativeblocks.io/gateway"

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
        NativeblocksManager.getInstance().liveKit()

        FoundationBlockProvider.provideBlocks()
        FoundationTypeProvider.provideTypes()
        NativeblocksManager.getInstance().provideEventLogger("LOGGER", AppLogger())
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