package io.nativeblocks.foundation.column

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import io.nativeblocks.core.util.findAlignmentHorizontal
import io.nativeblocks.core.util.findArrangementVertical
import io.nativeblocks.core.util.shapeMapper
import io.nativeblocks.core.util.widthAndHeight

@NativeBlock(
    keyType = "NATIVE_COLUMN",
    name = "Native Column",
    description = "Nativeblocks column block"
)
@Composable
fun NativeColumn(
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
    @NativeBlockProp(valuePicker = NativeBlockValuePicker.NUMBER_INPUT) paddingStart: Double = 8.0,
    @NativeBlockProp(valuePicker = NativeBlockValuePicker.NUMBER_INPUT) paddingTop: Double = 8.0,
    @NativeBlockProp(valuePicker = NativeBlockValuePicker.NUMBER_INPUT) paddingEnd: Double = 8.0,
    @NativeBlockProp(valuePicker = NativeBlockValuePicker.NUMBER_INPUT) paddingBottom: Double = 8.0,
    @NativeBlockProp(valuePicker = NativeBlockValuePicker.COLOR_PICKER) background: String = "#ffffffff",
    @NativeBlockProp(
        valuePicker = NativeBlockValuePicker.DROPDOWN,
        valuePickerOptions = [
            NativeBlockValuePickerOption("RTL", "RTL"),
            NativeBlockValuePickerOption("LTR", "LTR")
        ]
    ) direction: String = "LTR",
    @NativeBlockProp(valuePicker = NativeBlockValuePicker.NUMBER_INPUT) radiusTopStart: Double = 4.0,
    @NativeBlockProp(valuePicker = NativeBlockValuePicker.NUMBER_INPUT) radiusTopEnd: Double = 4.0,
    @NativeBlockProp(valuePicker = NativeBlockValuePicker.NUMBER_INPUT) radiusBottomStart: Double = 4.0,
    @NativeBlockProp(valuePicker = NativeBlockValuePicker.NUMBER_INPUT) radiusBottomEnd: Double = 4.0,
    @NativeBlockProp(
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("top", "top"),
            NativeBlockValuePickerOption("bottom", "bottom"),
            NativeBlockValuePickerOption("center", "center"),
            NativeBlockValuePickerOption("spaceBetween", "spaceBetween"),
            NativeBlockValuePickerOption("spaceAround", "spaceAround"),
            NativeBlockValuePickerOption("spaceEvenly", "spaceEvenly"),
        ]
    ) verticalArrangement: String = "top",
    @NativeBlockProp(
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("start", "start"),
            NativeBlockValuePickerOption("end", "end"),
            NativeBlockValuePickerOption("centerHorizontally", "centerHorizontally"),
        ]
    ) horizontalAlignment: String = "top",
    @NativeBlockEvent onClick: (() -> Unit)? = null,
    @NativeBlockSlot content: @Composable (index: BlockIndex) -> Unit
) {

    val shape = shapeMapper(
        "rectangle",
        radiusTopStart.toString(),
        radiusTopEnd.toString(),
        radiusBottomStart.toString(),
        radiusBottomEnd.toString(),
    )

    val modifier = Modifier
        .widthAndHeight(width, height)
        .background(Color(background.toColorInt()), shape)
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
        Column(
            modifier = modifier,
            verticalArrangement = findArrangementVertical(verticalArrangement),
            horizontalAlignment = findAlignmentHorizontal(horizontalAlignment)
        ) {
            content(-1)
        }
    }
}