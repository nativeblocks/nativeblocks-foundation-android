package io.nativeblocks.foundation.types

import androidx.compose.foundation.layout.Arrangement
import io.nativeblocks.core.api.provider.type.INativeType

class VerticalArrangementNativeType : INativeType<Arrangement.Vertical> {
    override fun toString(input: Arrangement.Vertical?): String {
        return when (input) {
            Arrangement.Top -> "top"
            Arrangement.Bottom -> "bottom"
            Arrangement.Center -> "center"
            Arrangement.SpaceBetween -> "spaceBetween"
            Arrangement.SpaceAround -> "spaceAround"
            Arrangement.SpaceEvenly -> "spaceEvenly"
            else -> "top"
        }
    }

    override fun fromString(input: String?): Arrangement.Vertical {
        return when (input?.lowercase()) {
            "top" -> Arrangement.Top
            "bottom" -> Arrangement.Bottom
            "center" -> Arrangement.Center
            "spacebetween" -> Arrangement.SpaceBetween
            "spacearound" -> Arrangement.SpaceAround
            "spaceevenly" -> Arrangement.SpaceEvenly
            else -> Arrangement.Top
        }
    }
}