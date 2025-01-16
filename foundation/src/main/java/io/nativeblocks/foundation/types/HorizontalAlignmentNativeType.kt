package io.nativeblocks.foundation.types

import androidx.compose.ui.Alignment
import io.nativeblocks.core.api.provider.type.INativeType

class HorizontalAlignmentNativeType : INativeType<Alignment.Horizontal> {
    override fun toString(input: Alignment.Horizontal?): String {
        return when (input) {
            Alignment.Start -> "start"
            Alignment.End -> "end"
            Alignment.CenterHorizontally -> "centerHorizontally"
            else -> "start"
        }
    }

    override fun fromString(input: String?): Alignment.Horizontal {
        return  when (input?.lowercase()) {
            "start" -> Alignment.Start
            "end" -> Alignment.End
            "centerhorizontally" -> Alignment.CenterHorizontally
            else -> Alignment.Start
        }
    }
}