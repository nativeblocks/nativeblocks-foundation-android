package io.nativeblocks.foundation.types

import androidx.compose.ui.text.style.TextAlign
import io.nativeblocks.core.api.provider.type.INativeType

class TextAlignNativeType : INativeType<TextAlign> {
    override fun toString(input: TextAlign?): String {
        return when (input) {
            TextAlign.Start -> "start"
            TextAlign.Center -> "center"
            TextAlign.End -> "end"
            TextAlign.Justify -> "justify"
            else -> ""
        }
    }

    override fun fromString(input: String?): TextAlign {
        return when (input?.lowercase()) {
            "start" -> TextAlign.Start
            "center" -> TextAlign.Center
            "end" -> TextAlign.End
            "justify" -> TextAlign.Justify
            else -> TextAlign.Start
        }
    }
}