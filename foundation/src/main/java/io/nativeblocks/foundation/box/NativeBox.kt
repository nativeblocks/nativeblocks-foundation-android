package io.nativeblocks.foundation.box

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import io.nativeblocks.core.util.findAlignment
import io.nativeblocks.core.util.widthAndHeight

@NativeBlock(
    keyType = "NATIVE_BOX",
    name = "Native Box",
    description = "Nativeblocks box block"
)
@Composable
fun NativeBox(
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
            NativeBlockValuePickerOption("center", "center"),
            NativeBlockValuePickerOption("centerStart", "centerStart"),
            NativeBlockValuePickerOption("centerEnd", "centerEnd"),
            NativeBlockValuePickerOption("bottomCenter", "bottomCenter"),
            NativeBlockValuePickerOption("bottomStart", "bottomStart"),
            NativeBlockValuePickerOption("bottomEnd", "bottomEnd"),
            NativeBlockValuePickerOption("topStart", "topStart"),
            NativeBlockValuePickerOption("topEnd", "topEnd"),
            NativeBlockValuePickerOption("topCenter", "topCenter")
        ]
    ) verticalAlignment: String = "center",
    @NativeBlockEvent onClick: (() -> Unit)? = null,
    @NativeBlockSlot content: @Composable (index: BlockIndex) -> Unit
) {
    val modifier = Modifier
        .clickable(enabled = onClick != null, onClick = {
            onClick?.invoke()
        })
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
        Box(
            modifier = modifier,
            contentAlignment = findAlignment(verticalAlignment),
        ) {
            content(-1)
        }
    }
}