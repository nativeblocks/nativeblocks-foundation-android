package io.nativeblocks.foundation

import androidx.compose.ui.unit.Dp
import io.nativeblocks.core.api.NativeblocksManager
import io.nativeblocks.foundation.types.DpNativeType

public object FoundationTypeProvider {
   public fun provideTypes() {
        NativeblocksManager.getInstance().provideTypeConverter(Dp::class, DpNativeType())
    }
}