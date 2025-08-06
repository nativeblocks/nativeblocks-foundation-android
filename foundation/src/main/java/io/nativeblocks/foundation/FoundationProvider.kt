package io.nativeblocks.foundation

import io.nativeblocks.foundation.actions.NativeChangeBlockProperty
import io.nativeblocks.foundation.actions.NativeChangeVariable
import io.nativeblocks.foundation.integration.consumer.action.FoundationActionProvider
import io.nativeblocks.foundation.integration.consumer.block.FoundationBlockProvider

/**
 * Provides foundational types, actions, and blocks to the Nativeblocks framework.
 * This object serves as a centralized provider for registering types, actions, and blocks
 * specific to the foundation module.
 */
object FoundationProvider {
    /**
     * Initializes and registers foundational types, actions, and blocks with the Nativeblocks framework.
     *
     * This function performs the following:
     * - Registers foundational types by invoking `FoundationTypeProvider.provideTypes()`.
     * - Registers actions, including:
     *   - [NativeChangeVariable]: An action for modifying variables with dynamic evaluation.
     *   - [NativeChangeBlockProperty]: An action for changing block properties.
     * - Registers foundational blocks by invoking `FoundationBlockProvider.provideBlocks()`.
     *
     * Call this method to ensure that the foundation module's functionalities are properly
     * integrated into the Nativeblocks framework.
     */
    fun provide(instanceName: String = "default") {
        FoundationTypeProvider.provideTypes(instanceName = instanceName)
        FoundationActionProvider.provideActions(
            instanceName = instanceName,
            nativeChangeVariable = NativeChangeVariable(),
            nativeChangeBlockProperty = NativeChangeBlockProperty()
        )
        FoundationBlockProvider.provideBlocks(instanceName = instanceName)
    }
}