package io.nativeblocks.foundation

import io.nativeblocks.foundation.actions.NativeChangeBlockProperty
import io.nativeblocks.foundation.actions.NativeChangeVariable
import io.nativeblocks.foundation.integration.consumer.action.FoundationActionProvider
import io.nativeblocks.foundation.integration.consumer.block.FoundationBlockProvider

object FoundationProvider {
    fun provide() {
        FoundationTypeProvider.provideTypes()
        FoundationActionProvider.provideActions(
            nativeChangeVariable = NativeChangeVariable(),
            nativeChangeBlockProperty = NativeChangeBlockProperty()
        )
        FoundationBlockProvider.provideBlocks()
    }
}