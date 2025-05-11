package io.nativeblocks.foundation.blocks.box

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.nativeblocks.compiler.type.BlockIndex
import io.nativeblocks.compiler.type.NativeBlock
import io.nativeblocks.compiler.type.NativeBlockEvent
import io.nativeblocks.compiler.type.NativeBlockProp
import io.nativeblocks.compiler.type.NativeBlockSlot
import io.nativeblocks.compiler.type.NativeBlockValuePicker
import io.nativeblocks.compiler.type.NativeBlockValuePickerOption
import io.nativeblocks.compiler.type.NativeBlockValuePickerPosition
import io.nativeblocks.foundation.util.shapeMapper
import io.nativeblocks.foundation.util.widthAndHeight

/**
 * A composable block for creating a customizable container with properties such as padding,
 * background color, corner radii, and alignment.
 *
 * This block is designed for server-driven UI, supporting dynamic properties, events, and slots.
 *
 * @param width Specifies the width of the box (e.g., "match" or "wrap"). Default is "wrap".
 * @param height Specifies the height of the box (e.g., "match" or "wrap"). Default is "wrap".
 * @param paddingStart Padding on the start side in DP. Default is 0.0.
 * @param paddingTop Padding on the top side in DP. Default is 0.0.
 * @param paddingEnd Padding on the end side in DP. Default is 0.0.
 * @param paddingBottom Padding on the bottom side in DP. Default is 0.0.
 * @param background Background color of the box in hexadecimal format. Default is "#00000000".
 * @param radiusTopStart Top-start corner radius in DP. Default is 0.0.
 * @param radiusTopEnd Top-end corner radius in DP. Default is 0.0.
 * @param radiusBottomStart Bottom-start corner radius in DP. Default is 0.0.
 * @param radiusBottomEnd Bottom-end corner radius in DP. Default is 0.0.
 * @param verticalAlignment Specifies the alignment of content inside the box. Default is "center".
 * @param onClick Callback triggered when the box is clicked. Default is null (disabled).
 * @param content Slot for composing child content within the box.
 */
@NativeBlock(
    keyType = "nativeblocks/BOX",
    name = "Native Box",
    description = "Nativeblocks box block",
    version = 1
)
@Composable
fun NativeBox(
    @NativeBlockProp(
        description = "Specifies the width of the box (e.g., 'match' or 'wrap').",
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ],
        defaultValue = "wrap"
    ) width: String = "wrap",
    @NativeBlockProp(
        description = "Specifies the height of the box (e.g., 'match' or 'wrap').",
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ],
        defaultValue = "wrap"
    ) height: String = "wrap",
    @NativeBlockProp(
        description = "Padding on the start side in DP.",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Spacing"),
        defaultValue = "0.0"
    ) paddingStart: Dp = 0.dp,
    @NativeBlockProp(
        description = "Padding on the top side in DP.",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Spacing"),
        defaultValue = "0.0"
    ) paddingTop: Dp = 0.dp,
    @NativeBlockProp(
        description = "Padding on the end side in DP.",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Spacing"),
        defaultValue = "0.0"
    ) paddingEnd: Dp = 0.dp,
    @NativeBlockProp(
        description = "Padding on the bottom side in DP.",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Spacing"),
        defaultValue = "0.0"
    ) paddingBottom: Dp = 0.dp,
    @NativeBlockProp(
        description = "Background color of the box in hexadecimal format (e.g., '#FFFFFF').",
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        defaultValue = "#00000000"
    ) background: Color = Color.Transparent,
    @NativeBlockProp(
        description = "Top-start corner radius of the box in DP.",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        defaultValue = "0.0"
    ) radiusTopStart: Dp = 0.dp,
    @NativeBlockProp(
        description = "Top-end corner radius of the box in DP.",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        defaultValue = "0.0"
    ) radiusTopEnd: Dp = 0.dp,
    @NativeBlockProp(
        description = "Bottom-start corner radius of the box in DP.",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        defaultValue = "0.0"
    ) radiusBottomStart: Dp = 0.dp,
    @NativeBlockProp(
        description = "Bottom-end corner radius of the box in DP.",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        defaultValue = "0.0"
    ) radiusBottomEnd: Dp = 0.dp,
    @NativeBlockProp(
        description = "Specifies the vertical alignment of content inside the box.",
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("center", "center"),
            NativeBlockValuePickerOption("centerStart", "centerStart"),
            NativeBlockValuePickerOption("centerEnd", "centerEnd"),
            NativeBlockValuePickerOption("bottomCenter", "bottomCenter"),
            NativeBlockValuePickerOption("bottomStart", "bottomStart"),
            NativeBlockValuePickerOption("bottomEnd", "bottomEnd"),
            NativeBlockValuePickerOption("topStart", "topStart"),
            NativeBlockValuePickerOption("topEnd", "topEnd"),
            NativeBlockValuePickerOption("topCenter", "topCenter")
        ],
        defaultValue = "center"
    ) verticalAlignment: Alignment = Alignment.Center,
    @NativeBlockEvent(
        description = "Triggered when the box is clicked."
    ) onClick: (() -> Unit)? = null,
    @NativeBlockSlot(
        description = "Slot for placing dynamic child content inside the box."
    ) content: @Composable (index: BlockIndex) -> Unit
) {
    val shape = shapeMapper(
        "rectangle",
        radiusTopStart,
        radiusTopEnd,
        radiusBottomStart,
        radiusBottomEnd,
    )

    var modifier = Modifier
        .widthAndHeight(width, height)
        .background(background, shape)
        .padding(
            start = paddingStart,
            top = paddingTop,
            end = paddingEnd,
            bottom = paddingBottom,
        )

    if (onClick != null) {
        modifier = Modifier.clickable(
            enabled = true,
            indication = null,
            interactionSource = remember { MutableInteractionSource() }) {
            onClick.invoke()
        }
    }

    Box(
        modifier = modifier,
        contentAlignment = verticalAlignment,
    ) {
        content(-1)
    }
}