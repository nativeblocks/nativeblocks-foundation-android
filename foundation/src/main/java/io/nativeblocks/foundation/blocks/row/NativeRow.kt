package io.nativeblocks.foundation.blocks.row

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
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
import io.nativeblocks.core.util.json.NativeJsonPath
import io.nativeblocks.foundation.util.shapeMapper
import io.nativeblocks.foundation.util.widthAndHeight

/**
 * A composable block for creating a customizable horizontal row layout with properties like padding,
 * background color, corner radii, scrolling behavior, and alignment.
 *
 * This block supports dynamic properties, events, and slots, making it ideal for server-driven UI.
 *
 * @param list A JSON array (e.g., "[{},{},...]") used to render child content dynamically. The size of the list determines
 * the number of repetitions of the content.
 * @param width The width of the row (e.g., "match" or "wrap"). Default is "wrap".
 * @param height The height of the row (e.g., "match" or "wrap"). Default is "wrap".
 * @param scrollable Determines if the row should be scrollable horizontally. Default is false.
 * @param paddingStart Padding on the start (left) side in DP. Default is 0.0.
 * @param paddingTop Padding on the top side in DP. Default is 0.0.
 * @param paddingEnd Padding on the end (right) side in DP. Default is 0.0.
 * @param paddingBottom Padding on the bottom side in DP. Default is 0.0.
 * @param background Background color of the row in hexadecimal format. Default is "#00000000".
 * @param radiusTopStart Top-start corner radius in DP. Default is 0.0.
 * @param radiusTopEnd Top-end corner radius in DP. Default is 0.0.
 * @param radiusBottomStart Bottom-start corner radius in DP. Default is 0.0.
 * @param radiusBottomEnd Bottom-end corner radius in DP. Default is 0.0.
 * @param direction The layout direction of the row (e.g., "LTR" or "RTL"). Default is "LTR".
 * @param horizontalArrangement Horizontal arrangement of child components inside the row. Default is "start".
 * @param verticalAlignment Vertical alignment of child components inside the row. Default is "top".
 * @param onClick Callback triggered when the row is clicked. Default is null (disabled).
 * @param content Slot for composing child content within the row.
 */
@NativeBlock(
    keyType = "NATIVE_ROW",
    name = "Native Row",
    description = "Nativeblocks row block",
    version = 2
)
@Composable
fun NativeRow(
    @NativeBlockData(
        "A JSON array (e.g., '[{},{},...]') used for repeating the content based on its size. If the list value is invalid, the default content slot is invoked."
    )
    list: String = "",
    @NativeBlockProp(
        description = "The width of the row (e.g., 'match' or 'wrap').",
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ],
        defaultValue = "wrap"
    ) width: String = "wrap",
    @NativeBlockProp(
        description = "The height of the row (e.g., 'match' or 'wrap').",
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ],
        defaultValue = "wrap"
    ) height: String = "wrap",
    @NativeBlockProp(
        description = "Determines if the row should be scrollable horizontally.",
        valuePicker = NativeBlockValuePicker.DROPDOWN,
        valuePickerOptions = [
            NativeBlockValuePickerOption("false", "false"),
            NativeBlockValuePickerOption("true", "true")
        ],
        defaultValue = "false"
    ) scrollable: Boolean = false,
    @NativeBlockProp(
        description = "Padding on the start (left) side in DP.",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Spacing"),
        defaultValue = "0.0"
    ) paddingStart: Double = 0.0,
    @NativeBlockProp(
        description = "Padding on the top side in DP.",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Spacing"),
        defaultValue = "0.0"
    ) paddingTop: Double = 0.0,
    @NativeBlockProp(
        description = "Padding on the end (right) side in DP.",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Spacing"),
        defaultValue = "0.0"
    ) paddingEnd: Double = 0.0,
    @NativeBlockProp(
        description = "Padding on the bottom side in DP.",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Spacing"),
        defaultValue = "0.0"
    ) paddingBottom: Double = 0.0,
    @NativeBlockProp(
        description = "Background color of the row in hexadecimal format.",
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        defaultValue = "#00000000"
    ) background: Color = Color.Transparent,
    @NativeBlockProp(
        description = "Top-start corner radius in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        defaultValue = "0.0"
    ) radiusTopStart: Double = 0.0,
    @NativeBlockProp(
        description = "Top-end corner radius in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        defaultValue = "0.0"
    ) radiusTopEnd: Double = 0.0,
    @NativeBlockProp(
        description = "Bottom-start corner radius in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        defaultValue = "0.0"
    ) radiusBottomStart: Double = 0.0,
    @NativeBlockProp(
        description = "Bottom-end corner radius in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        defaultValue = "0.0"
    ) radiusBottomEnd: Double = 0.0,
    @NativeBlockProp(
        description = "The layout direction of the row (e.g., 'LTR' or 'RTL').",
        valuePicker = NativeBlockValuePicker.DROPDOWN,
        valuePickerOptions = [
            NativeBlockValuePickerOption("RTL", "RTL"),
            NativeBlockValuePickerOption("LTR", "LTR")
        ],
        defaultValue = "LTR"
    ) direction: LayoutDirection = LayoutDirection.Ltr,
    @NativeBlockProp(
        description = "Horizontal arrangement of child components inside the row.",
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("start", "start"),
            NativeBlockValuePickerOption("end", "end"),
            NativeBlockValuePickerOption("center", "center"),
            NativeBlockValuePickerOption("spaceBetween", "spaceBetween"),
            NativeBlockValuePickerOption("spaceAround", "spaceAround"),
            NativeBlockValuePickerOption("spaceEvenly", "spaceEvenly"),
        ],
        defaultValue = "start"
    ) horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    @NativeBlockProp(
        description = "Vertical alignment of child components inside the row.",
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("top", "top"),
            NativeBlockValuePickerOption("bottom", "bottom"),
            NativeBlockValuePickerOption("centerVertically", "centerVertically"),
        ],
        defaultValue = "top"
    ) verticalAlignment: Alignment.Vertical = Alignment.Top,
    @NativeBlockEvent(
        description = "Callback triggered when the row is clicked."
    ) onClick: (() -> Unit)? = null,
    @NativeBlockSlot(
        description = "Slot for composing child content within the row."
    ) content: @Composable (index: BlockIndex) -> Unit
) {
    val listItems: List<*>? = try {
        NativeJsonPath().query(list, "$") as List<*>
    } catch (e: Exception) {
        null
    }

    val shape = shapeMapper(
        "rectangle",
        radiusTopStart.dp,
        radiusTopEnd.dp,
        radiusBottomStart.dp,
        radiusBottomEnd.dp,
    )
    var modifier = Modifier
        .widthAndHeight(width, height)
        .background(background, shape)
        .padding(
            start = paddingStart.dp,
            top = paddingTop.dp,
            end = paddingEnd.dp,
            bottom = paddingBottom.dp,
        )

    if (onClick != null) {
        modifier = Modifier.clickable(
            enabled = true,
            indication = null,
            interactionSource = remember { MutableInteractionSource() }) {
            onClick.invoke()
        }
    }

    if (scrollable) {
        modifier = modifier.horizontalScroll(rememberScrollState())
    }

    CompositionLocalProvider(LocalLayoutDirection provides direction) {
        Row(
            modifier = modifier,
            verticalAlignment = verticalAlignment,
            horizontalArrangement = horizontalArrangement
        ) {
            if (listItems != null) {
                listItems.forEachIndexed { index, _ ->
                    content.invoke(index)
                }
            } else {
                content(-1)
            }
        }
    }
}