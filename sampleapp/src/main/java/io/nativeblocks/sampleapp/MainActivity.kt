package io.nativeblocks.sampleapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import io.nativeblocks.core.api.NativeblocksEdition
import io.nativeblocks.core.api.NativeblocksError
import io.nativeblocks.core.api.NativeblocksFrame
import io.nativeblocks.core.api.NativeblocksLoading
import io.nativeblocks.core.api.NativeblocksManager
import io.nativeblocks.core.api.provider.logger.INativeLogger
import io.nativeblocks.foundation.integration.consumer.block.FoundationBlockProvider
import io.nativeblocks.wandkit.liveKit
import kotlinx.coroutines.launch

private const val NATIVEBLOCKS_API_KEY = ""
private const val NATIVEBLOCKS_API_URL = "https://edge.api.nativeblocks.io/gateway"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        lifecycleScope.launch {
            NativeblocksManager.getInstance().getScaffold({
                // register frames in graph for owning navigation
            }, {
                // there is no frame to register
            })
        }

        NativeblocksManager.getInstance().provideEventLogger("LOGGER", AppLogger())
        setContent {
            NativeblocksFrame(
                frameRoute = "/",
                routeArguments = hashMapOf(),
                loading = {
                    NativeblocksLoading()
                },
                error = { message ->
                    NativeblocksError(message)
                },
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        NativeblocksManager.getInstance().destroy()
    }
}

class AppLogger : INativeLogger {
    override fun log(eventName: String, parameters: Map<String, String>) {
        Log.d(eventName, "log: $parameters")
    }
}
