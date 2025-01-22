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
        return when (input?.lowercase()) {
            "thin" -> FontWeight.Thin
            "extralight" -> FontWeight.ExtraLight
            "light" -> FontWeight.Light
            "normal" -> FontWeight.Normal
            "medium" -> FontWeight.Medium
            "semibold" -> FontWeight.SemiBold
            "bold" -> FontWeight.Bold
            "extrabold" -> FontWeight.ExtraBold
            "black" -> FontWeight.Black
            else -> FontWeight.Normal
        }
    }
}