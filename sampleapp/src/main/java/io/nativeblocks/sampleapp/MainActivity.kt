package io.nativeblocks.sampleapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import io.nativeblocks.core.api.NativeblocksError
import io.nativeblocks.core.api.NativeblocksFrame
import io.nativeblocks.core.api.NativeblocksLoading
import io.nativeblocks.core.api.NativeblocksManager
import io.nativeblocks.core.api.provider.logger.INativeLogger
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            NativeblocksManager.getInstance().getScaffold({
                // register frames in graph for owning navigation
            }, {
                // there is no frame to register
            })
        }

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
