package io.nativeblocks.foundation.blocks.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.nativeblocks.compiler.type.NativeBlock
import io.nativeblocks.compiler.type.NativeBlockProp
import io.nativeblocks.compiler.type.NativeBlockValuePicker
import io.nativeblocks.compiler.type.NativeBlockValuePickerOption
import io.nativeblocks.compiler.type.NativeBlockValuePickerPosition
import io.nativeblocks.core.api.provider.block.BlockProps
import io.nativeblocks.foundation.util.blockWeight
import io.nativeblocks.foundation.util.widthAndHeight

/**
 * A composable block for creating an empty space with configurable width and height, designed to be used as a spacer in layouts.
 *
 * **NativeSpacer** is a flexible spacer block that integrates with the Nativeblocks ecosystem. It supports dynamic properties
 * for width and height, making it useful for adding spacing between components.
 *
 * ### Features:
 * - Configurable width and height.
 * - Supports dynamic properties for responsive layouts.
 *
 * @param width The width of the spacer (e.g., "match" or "wrap"). Default is "wrap".
 * @param height The height of the spacer (e.g., "match" or "wrap"). Default is "wrap".
 * @param weight Specifies the weight of the layout in row or column. Default is 0.0 means not set..
 */
@NativeBlock(
    keyType = "nativeblocks/SPACER",
    name = "Native Spacer",
    description = "Nativeblocks spacer block",
    version = 1
)
@Composable
fun NativeSpacer(
    blockProps: BlockProps? = null,
    @NativeBlockProp(
        description = "The width of the spacer (e.g., 'match' or 'wrap').",
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ],
        defaultValue = "wrap"
    ) width: String = "wrap",
    @NativeBlockProp(
        description = "The height of the spacer (e.g., 'match' or 'wrap').",
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ],
        defaultValue = "wrap"
    ) height: String = "wrap",
    @NativeBlockProp(
        description = "Specifies the weight of the layout in row or column. Default is 0.0 means not set.",
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        defaultValue = "0F"
    ) weight: Float = 0F,
) {
    Spacer(
        modifier = Modifier
            .widthAndHeight(width, height)
            .blockWeight(weight, blockProps?.hierarchy?.last()?.scope)
    )
}