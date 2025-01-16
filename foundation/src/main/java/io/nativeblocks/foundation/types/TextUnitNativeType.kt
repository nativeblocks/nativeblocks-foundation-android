package io.nativeblocks.foundation.types

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import io.nativeblocks.core.api.provider.type.INativeType

class TextUnitNativeType : INativeType<TextUnit> {
    override fun toString(input: TextUnit?): String {
        return when (input?.type) {
            TextUnitType.Em -> {
                "${input.value.toDouble()}.em"
            }
            TextUnitType.Sp -> {
                "${input.value.toDouble()}.sp"
            }
            else -> input?.value?.toDouble()?.toString() ?: "0"
        }
    }

    override fun fromString(input: String?): TextUnit {
        return if (input?.endsWith(".em", ignoreCase = true) == true) {
            input.replace(".em", "", ignoreCase = true).toDoubleOrNull()?.em ?: 0.em
        } else {
            input?.replace(".sp", "", ignoreCase = true)?.toDoubleOrNull()?.sp ?: 0.sp
        }
    }
}