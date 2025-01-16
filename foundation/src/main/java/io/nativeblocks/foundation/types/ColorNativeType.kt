package io.nativeblocks.foundation.types

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import io.nativeblocks.core.api.provider.type.INativeType

class ColorNativeType : INativeType<Color> {
    override fun toString(input: Color?): String {
        return input?.let {
            val alpha = (it.alpha * 255).toInt()
            val red = (it.red * 255).toInt()
            val green = (it.green * 255).toInt()
            val blue = (it.blue * 255).toInt()
            String.format("#%02X%02X%02X%02X", alpha, red, green, blue)
        } ?: "#00000000"
    }

    override fun fromString(input: String?): Color {
        return input?.toColorInt()?.let { Color(it) } ?: Color.Transparent
    }
}