package io.nativeblocks.foundation.types

import androidx.compose.ui.Alignment
import io.nativeblocks.core.api.provider.type.INativeType

class VerticalAlignmentNativeType : INativeType<Alignment.Vertical> {
    override fun toString(input: Alignment.Vertical?): String {
        return when (input) {
            Alignment.Top -> "top"
            Alignment.Bottom -> "bottom"
            Alignment.CenterVertically -> "centerVertically"
            else -> ""
        }
    }

    override fun fromString(input: String?): Alignment.Vertical {
        return when (input?.lowercase()) {
            "top" -> Alignment.Top
            "bottom" -> Alignment.Bottom
            "centervertically" -> Alignment.CenterVertically
            else -> Alignment.Top
        }
    }
}