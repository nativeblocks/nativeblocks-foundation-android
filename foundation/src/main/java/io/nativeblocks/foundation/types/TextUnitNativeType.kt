package io.nativeblocks.foundation.types

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import io.nativeblocks.core.api.provider.type.INativeType

/**
 * A class that implements [INativeType] to handle [TextUnit] conversion to and from strings.
 * This class is used to represent the [TextUnit] type in a string format, with support for "sp" units.
 *
 * Example usage:
 * ```
 * NativeblocksManager.getInstance()
 *     .provideTypeConverter(TextUnit::class, TextUnitNativeType())
 * ```
 * In this example, the [TextUnitNativeType] is registered with the [NativeblocksManager] as a type converter
 * for the [TextUnit] class, enabling seamless conversion between [TextUnit] and its string representation.
 */
class TextUnitNativeType : INativeType<TextUnit> {

    /**
     * Converts the given [TextUnit] to a string representation.
     *
     * @param input The [TextUnit] value to convert.
     * @return A string representation of the [TextUnit] value, including units "sp".
     *         For example, "16.0.sp".
     */
    override fun toString(input: TextUnit?): String {
        return when (input?.type) {
            TextUnitType.Sp -> "${input.value.toDouble()}.sp"
            else -> input?.value?.toDouble()?.toString() ?: "0"
        }
    }

    /**
     * Converts the given string to the corresponding [TextUnit].
     *
     * @param input The string to convert, which should include a unit like "sp".
     *              For example, "16.0.sp".
     * @return The corresponding [TextUnit] value, [TextUnit.Sp].
     *         Defaults to `0.sp` if the string is invalid.
     */
    override fun fromString(input: String?): TextUnit {
        return input?.replace(".sp", "", ignoreCase = true)?.toDoubleOrNull()?.sp ?: 0.sp
    }
}