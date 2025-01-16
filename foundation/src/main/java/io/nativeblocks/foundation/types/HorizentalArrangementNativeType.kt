package io.nativeblocks.foundation.types

import androidx.compose.foundation.layout.Arrangement
import io.nativeblocks.core.api.provider.type.INativeType

class HorizontalArrangementNativeType : INativeType<Arrangement.Horizontal> {
    override fun toString(input: Arrangement.Horizontal?): String {
        return when (input) {
            Arrangement.Start -> "start"
            Arrangement.End -> "end"
            Arrangement.Center -> "center"
            else -> "start"
        }
    }

    override fun fromString(input: String?): Arrangement.Horizontal {
        return  when (input) {
            "start" -> Arrangement.Start
            "end" -> Arrangement.End
            "center" -> Arrangement.Center
            else -> Arrangement.Start
        }
    }
}