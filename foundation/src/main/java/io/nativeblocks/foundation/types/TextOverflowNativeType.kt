package io.nativeblocks.foundation.types

import androidx.compose.ui.text.style.TextOverflow
import io.nativeblocks.core.api.provider.type.INativeType

class TextOverflowNativeType : INativeType<TextOverflow> {
    override fun toString(input: TextOverflow?): String {
        return when (input) {
            TextOverflow.Clip -> "clip"
            TextOverflow.Ellipsis -> "ellipsis"
            TextOverflow.Visible -> "visible"
            else -> ""
        }
    }

    override fun fromString(input: String?): TextOverflow {
        return when (input?.lowercase()) {
            "clip" -> TextOverflow.Clip
            "ellipsis" -> TextOverflow.Ellipsis
            "visible" -> TextOverflow.Visible
            else -> TextOverflow.Clip
        }

    }
}