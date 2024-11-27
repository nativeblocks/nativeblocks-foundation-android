package io.nativeblocks.foundation.row

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import io.nativeblocks.compiler.type.BlockIndex
import io.nativeblocks.compiler.type.NativeBlock
import io.nativeblocks.compiler.type.NativeBlockEvent
import io.nativeblocks.compiler.type.NativeBlockProp
import io.nativeblocks.compiler.type.NativeBlockSlot
import io.nativeblocks.compiler.type.NativeBlockValuePicker
import io.nativeblocks.compiler.type.NativeBlockValuePickerOption
import io.nativeblocks.core.util.findAlignmentVertical
import io.nativeblocks.core.util.findArrangementHorizontal
import io.nativeblocks.core.util.widthAndHeight

@NativeBlock(
    keyType = "NATIVE_ROW",
    name = "Native Row",
    description = "Nativeblocks row block"
)
@Composable
fun NativeRow(
    @NativeBlockProp(
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ]
    ) width: String = "wrap",
    @NativeBlockProp(
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ]
    ) height: String = "wrap",
    @NativeBlockProp(valuePicker = NativeBlockValuePicker.NUMBER_INPUT) paddingStart: Double = 0.0,
    @NativeBlockProp(valuePicker = NativeBlockValuePicker.NUMBER_INPUT) paddingTop: Double = 0.0,
    @NativeBlockProp(valuePicker = NativeBlockValuePicker.NUMBER_INPUT) paddingEnd: Double = 0.0,
    @NativeBlockProp(valuePicker = NativeBlockValuePicker.NUMBER_INPUT) paddingBottom: Double = 0.0,
    @NativeBlockProp(valuePicker = NativeBlockValuePicker.COLOR_PICKER) background: String = "#00000000",
    @NativeBlockProp(
        valuePicker = NativeBlockValuePicker.DROPDOWN,
        valuePickerOptions = [
            NativeBlockValuePickerOption("RTL", "RTL"),
            NativeBlockValuePickerOption("LTR", "LTR")
        ]
    ) direction: String = "LTR",
    @NativeBlockProp(
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("start", "start"),
            NativeBlockValuePickerOption("end", "end"),
            NativeBlockValuePickerOption("center", "center"),
            NativeBlockValuePickerOption("spaceBetween", "spaceBetween"),
            NativeBlockValuePickerOption("spaceAround", "spaceAround"),
            NativeBlockValuePickerOption("spaceEvenly", "spaceEvenly"),
        ]
    ) horizontalArrangement: String = "start",
    @NativeBlockProp(
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("top", "top"),
            NativeBlockValuePickerOption("bottom", "bottom"),
            NativeBlockValuePickerOption("centerVertically", "centerVertically"),
        ]
    ) verticalAlignment: String = "top",
    @NativeBlockEvent onClick: (() -> Unit)? = null,
    @NativeBlockSlot content: @Composable (index: BlockIndex) -> Unit
) {
    val modifier = Modifier
        .widthAndHeight(width, height)
        .background(Color(background.toColorInt()))
        .padding(
            start = paddingStart.dp,
            top = paddingTop.dp,
            end = paddingEnd.dp,
            bottom = paddingBottom.dp,
        )

    val blockDirection = if (direction == "RTL") {
        LocalLayoutDirection provides LayoutDirection.Rtl
    } else {
        LocalLayoutDirection provides LayoutDirection.Ltr
    }
    CompositionLocalProvider(blockDirection) {
        Row(
            modifier = modifier
                .clickable(enabled = onClick != null, onClick = {
                    onClick?.invoke()
                }),
            verticalAlignment = findAlignmentVertical(verticalAlignment),
            horizontalArrangement = findArrangementHorizontal(horizontalArrangement)
        ) {
            content(-1)
        }
    }
}