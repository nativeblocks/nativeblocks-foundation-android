package io.nativeblocks.foundation.types

import androidx.compose.ui.unit.LayoutDirection
import io.nativeblocks.core.api.provider.type.INativeType

class LayoutDirectionNativeType : INativeType<LayoutDirection> {
    override fun toString(input: LayoutDirection?): String {
        return if (input == LayoutDirection.Rtl) {
            "RTL"
        } else {
            "LTR"
        }
    }

    override fun fromString(input: String?): LayoutDirection {
        return if (input == "RTL") {
            LayoutDirection.Rtl
        } else {
            LayoutDirection.Ltr
        }
    }
}