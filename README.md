# NativeblocksFoundationAndroid

[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE) [![GitHub stars](https://img.shields.io/github/stars/nativeblocks/nativeblocks-foundation-android.svg)](https://github.com/nativeblocks/nativeblocks-foundation-android/stargazers) [![GitHub issues](https://img.shields.io/github/issues/nativeblocks/nativeblocks-foundation-android.svg)](https://github.com/nativeblocks/nativeblocks-foundation-android/issues) [![GitHub forks](https://img.shields.io/github/forks/nativeblocks/nativeblocks-foundation-android.svg)](https://github.com/nativeblocks/nativeblocks-foundation-android/network)

**NativeblocksFoundation** is a foundational Compose library that provides essential building blocks for the
Nativeblocks ecosystem. It enables developers to create flexible, dynamic UIs with ease using pre-configured,
server-driven blocks that are highly customizable. Below is an overview of the blocks available in *
*NativeblocksFoundation**.

For comprehensive details about Nativeblocks, please refer to the
main [Nativeblocks documentation](https://nativeblocks.io/docs/get-started/introduction/).

---

## Available Blocks

The NativeblocksFoundationAndroid library includes several reusable and customizable blocks designed to simplify UI
development. Here are the components included in this library:

### 1. Native Box Block

- **Name**: Native Box
- **Key Type**: `NATIVE_BOX`
- **Description**: A flexible container for arranging child views, used as a base layout block.

### 2. Native Button Block

- **Name**: Native Button
- **Key Type**: `NATIVE_BUTTON`
- **Description**: A customizable button with options for text, icons, and actions.

### 3. Native Column Block

- **Name**: Native Column
- **Key Type**: `NATIVE_COLUMN`
- **Description**: A vertical stack container for arranging child views in a column.

### 4. Native Image Block

- **Name**: Native Image
- **Key Type**: `NATIVE_IMAGE`
- **Description**: Displays images with options for placeholders and error views.

### 5. Native Row Block

- **Name**: Native Row
- **Key Type**: `NATIVE_ROW`
- **Description**: A horizontal stack container for arranging child views in a row.

### 6. Native Spacer Block

- **Name**: Native Spacer
- **Key Type**: `NATIVE_SPACER`
- **Description**: A flexible spacer for expanding views.

### 7. Native Text Block

- **Name**: Native Text
- **Key Type**: `NATIVE_TEXT`
- **Description**: A text display block with rich formatting options.

### 8. Native TextField Block

- **Name**: Native Text Field
- **Key Type**: `NATIVE_TEXT_FIELD`
- **Description**: A text field for user input with support for multiple keyboard types and editing events.---

## Getting Started

To integrate **NativeblocksFoundation** into your project, add it via Gradle:

1. Add the library to your `build.gradle` file:

    ```gradle
    implementation("io.nativeblocks:nativeblocks-foundation-android:1.0.0")
    ```

2. Provide nativeblocks foundation blocks :

    ```kotlin
    FoundationBlockProvider.provideBlocks()
    ```

3. Start using the blocks provided by **NativeblocksFoundation** to create dynamic, server-driven UIs.

### Sample App Example

Here is an example of a basic Android app using **NativeblocksFoundationAndroid**:

```kotlin
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
        )

        FoundationBlockProvider.provideBlocks()

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
```

---

## License

**NativeblocksFoundation** is licensed under the [MIT License](LICENSE). You are free to use, modify, and distribute
this library under the license terms.

---

## Additional Resources

- [Nativeblocks Documentation](https://nativeblocks.io/docs/get-started/introduction/)
- [Nativeblocks Compiler](https://nativeblocks.io/docs/compiler/kotlin-block/)
- [Getting Started with Server-Driven UI](https://nativeblocks.io/blog/server-driven-ui-introduction/)

For any questions or issues, feel free to open an issue in the repository. Happy coding!

