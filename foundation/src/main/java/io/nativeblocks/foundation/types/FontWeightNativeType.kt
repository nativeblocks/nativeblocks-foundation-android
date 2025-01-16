package io.nativeblocks.foundation.types

import androidx.compose.ui.text.font.FontWeight
import io.nativeblocks.core.api.provider.type.INativeType

class FontWeightNativeType : INativeType<FontWeight> {
    override fun toString(input: FontWeight?): String {
        return when (input) {
            FontWeight.Thin -> "thin"
            FontWeight.ExtraLight -> "extraLight"
            FontWeight.Light -> "light"
            FontWeight.Normal -> "normal"
            FontWeight.Medium -> "medium"
            FontWeight.SemiBold -> "semiBold"
            FontWeight.Bold -> "bold"
            FontWeight.ExtraBold -> "extraBold"
            FontWeight.Black -> "black"
            else -> ""
        }
    }

    override fun fromString(input: String?): FontWeight {
        return when (input) {
            "thin" -> FontWeight.Thin
            "extraLight" -> FontWeight.ExtraLight
            "light" -> FontWeight.Light
            "normal" -> FontWeight.Normal
            "medium" -> FontWeight.Medium
            "semiBold" -> FontWeight.SemiBold
            "bold" -> FontWeight.Bold
            "extraBold" -> FontWeight.ExtraBold
            "black" -> FontWeight.Black
            else -> FontWeight.Normal
        }
    }
}