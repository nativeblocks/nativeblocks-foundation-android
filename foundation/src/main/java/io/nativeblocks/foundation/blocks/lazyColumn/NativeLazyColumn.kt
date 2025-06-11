package io.nativeblocks.foundation.blocks.lazyColumn

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.nativeblocks.compiler.type.BlockIndex
import io.nativeblocks.compiler.type.NativeBlock
import io.nativeblocks.compiler.type.NativeBlockData
import io.nativeblocks.compiler.type.NativeBlockEvent
import io.nativeblocks.compiler.type.NativeBlockProp
import io.nativeblocks.compiler.type.NativeBlockSlot
import io.nativeblocks.compiler.type.NativeBlockValuePicker
import io.nativeblocks.compiler.type.NativeBlockValuePickerOption
import io.nativeblocks.compiler.type.NativeBlockValuePickerPosition
import io.nativeblocks.core.api.provider.block.BlockProps
import io.nativeblocks.foundation.util.blockWeight
import io.nativeblocks.foundation.util.shapeMapper
import io.nativeblocks.foundation.util.widthAndHeight

/**
 * A composable block for creating a customizable vertical lazy column list layout with properties like padding, background, corner radii, scrolling behavior, and alignment.
 *
 * This block supports dynamic properties, events, and slots, making it ideal for server-driven UI.
 *
 * @param length The Length of list determines the number of repetitions of the content.
 * @param width The width of the column (e.g., "match" or "wrap"). Default is "wrap".
 * @param weight Specifies the weight of the layout in row or column. Default is 0.0 means not set..
 * @param scrollable Determines if the column should be scrollable. Default is false.
 * @param height The height of the column (e.g., "match" or "wrap"). Default is "wrap".
 * @param paddingStart Padding on the start (left) side in DP. Default is 0.0.
 * @param paddingTop Padding on the top side in DP. Default is 0.0.
 * @param paddingEnd Padding on the end (right) side in DP. Default is 0.0.
 * @param paddingBottom Padding on the bottom side in DP. Default is 0.0.
 * @param backgroundColor Background color of the column in hexadecimal format. Default is "#00000000".
 * @param borderColor border color of the column in hexadecimal format. Default is "#00000000".
 * @param radiusTopStart Top-start corner radius in DP. Default is 0.0.
 * @param radiusTopEnd Top-end corner radius in DP. Default is 0.0.
 * @param radiusBottomStart Bottom-start corner radius in DP. Default is 0.0.
 * @param radiusBottomEnd Bottom-end corner radius in DP. Default is 0.0.
 * @param verticalArrangement Vertical arrangement of child components inside the column. Default is "top".
 * @param horizontalAlignment Horizontal alignment of child components inside the column. Default is "start".
 * @param onClick Callback triggered when the column is clicked. Default is null (disabled).
 * @param content Slot for composing child content within the column.
 */
@NativeBlock(
    keyType = "nativeblocks/LAZY_COLUMN",
    name = "Native Lazy Column",
    description = "Nativeblocks lazy column block",
    version = 1,
    versionName = "1"
)
@Composable
fun NativeLazyColumn(
    blockProps: BlockProps? = null,
    @NativeBlockData(
        description = "The Length of list determines the number of repetitions of the content.",
        defaultValue = "0"
    )
    length: Int = 0,
    @NativeBlockProp(
        description = "The width of the column (e.g., 'match' or 'wrap').",
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ],
        defaultValue = "wrap"
    ) width: String = "wrap",
    @NativeBlockProp(
        description = "The height of the column (e.g., 'match' or 'wrap').",
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
    @NativeBlockProp(
        description = "Determines if the column should be scrollable.",
        valuePicker = NativeBlockValuePicker.DROPDOWN,
        valuePickerOptions = [
            NativeBlockValuePickerOption("false", "false"),
            NativeBlockValuePickerOption("true", "true")
        ],
        defaultValue = "true"
    ) scrollable: Boolean = true,

    @NativeBlockProp(
        description = "Padding on the start (left) side in DP.",
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
        description = "Padding on the end (right) side in DP.",
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
        description = "Background color of the column in hexadecimal format.",
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        defaultValue = "#00000000"
    ) backgroundColor: Color = Color.Transparent,
    @NativeBlockProp(
        description = "border color of the column in hexadecimal format.",
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        defaultValue = "#00000000"
    ) borderColor: Color = Color.Transparent,
    @NativeBlockProp(
        description = "Top-start corner radius in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        defaultValue = "0.0"
    ) radiusTopStart: Dp = 0.dp,
    @NativeBlockProp(
        description = "Top-end corner radius in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        defaultValue = "0.0"
    ) radiusTopEnd: Dp = 0.dp,
    @NativeBlockProp(
        description = "Bottom-start corner radius in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        defaultValue = "0.0"
    ) radiusBottomStart: Dp = 0.dp,
    @NativeBlockProp(
        description = "Bottom-end corner radius in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        defaultValue = "0.0"
    ) radiusBottomEnd: Dp = 0.dp,
    @NativeBlockProp(
        description = "Vertical arrangement of child components inside the column.",
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("top", "top"),
            NativeBlockValuePickerOption("bottom", "bottom"),
            NativeBlockValuePickerOption("center", "center"),
            NativeBlockValuePickerOption("spaceBetween", "spaceBetween"),
            NativeBlockValuePickerOption("spaceAround", "spaceAround"),
            NativeBlockValuePickerOption("spaceEvenly", "spaceEvenly"),
        ],
        defaultValue = "top"
    ) verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    @NativeBlockProp(
        description = "Horizontal alignment of child components inside the column.",
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("start", "start"),
            NativeBlockValuePickerOption("end", "end"),
            NativeBlockValuePickerOption("centerHorizontally", "centerHorizontally"),
        ],
        defaultValue = "start"
    ) horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    @NativeBlockEvent(
        description = "Callback triggered when the column is clicked."
    ) onClick: (() -> Unit)? = null,
    @NativeBlockSlot(
        description = "Slot for composing child content within the column."
    ) content: @Composable (index: BlockIndex, scope: Any) -> Unit
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
        .background(backgroundColor, shape)
        .border(1.dp, borderColor, shape)
        .padding(
            start = paddingStart,
            top = paddingTop,
            end = paddingEnd,
            bottom = paddingBottom,
        )
        .blockWeight(weight, blockProps?.hierarchy?.last()?.scope)

    if (onClick != null) {
        modifier = Modifier.clickable(
            enabled = true,
            indication = null,
            interactionSource = remember { MutableInteractionSource() }) {
            onClick.invoke()
        }
    }

    LazyColumn(
        modifier = modifier,
        userScrollEnabled = scrollable,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        items(count = length) { index ->
            content.invoke(index, this@LazyColumn)
        }
    }
}