package io.nativeblocks.foundation.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import io.nativeblocks.compiler.type.NativeBlock
import io.nativeblocks.compiler.type.NativeBlockData
import io.nativeblocks.compiler.type.NativeBlockProp
import io.nativeblocks.compiler.type.NativeBlockValuePicker
import io.nativeblocks.compiler.type.NativeBlockValuePickerOption
import io.nativeblocks.compiler.type.NativeBlockValuePickerPosition
import io.nativeblocks.core.util.fontFamilyMapper
import io.nativeblocks.core.util.fontWeightMapper
import io.nativeblocks.core.util.textAlignmentMapper
import io.nativeblocks.core.util.textOverflowMapper
import io.nativeblocks.core.util.typographyBuilder
import io.nativeblocks.core.util.widthAndHeight

@NativeBlock(
    keyType = "NATIVE_TEXT",
    name = "Native Text",
    description = "Nativeblocks text block",
    version = 2
)
@Composable
fun NativeText(
    @NativeBlockData text: String,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ]
    ) width: String = "wrap",
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ]
    ) height: String = "wrap",
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Font")
    ) fontFamily: String = "default",
    @NativeBlockProp(
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Font")
    ) fontSize: Double = 14.0,
    @NativeBlockProp(
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        valuePickerGroup = NativeBlockValuePickerPosition("Font")
    ) textColor: String = "#ffffffff",
    @NativeBlockProp(
        valuePicker = NativeBlockValuePicker.DROPDOWN,
        valuePickerOptions = [
            NativeBlockValuePickerOption("start", "start"),
            NativeBlockValuePickerOption("center", "center"),
            NativeBlockValuePickerOption("end", "end"),
            NativeBlockValuePickerOption("justify", "justify")
        ],
        valuePickerGroup = NativeBlockValuePickerPosition("Font")
    ) textAlign: String = "start",
    @NativeBlockProp(
        valuePicker = NativeBlockValuePicker.DROPDOWN,
        valuePickerOptions = [
            NativeBlockValuePickerOption("thin", "thin"),
            NativeBlockValuePickerOption("extraLight", "extraLight"),
            NativeBlockValuePickerOption("light", "light"),
            NativeBlockValuePickerOption("normal", "normal"),
            NativeBlockValuePickerOption("medium", "medium"),
            NativeBlockValuePickerOption("semiBold", "semiBold"),
            NativeBlockValuePickerOption("bold", "bold"),
            NativeBlockValuePickerOption("extraBold", "extraBold"),
            NativeBlockValuePickerOption("black", "black")
        ],
        valuePickerGroup = NativeBlockValuePickerPosition("Font")
    ) fontWeight: String = "normal",
    @NativeBlockProp(
        valuePicker = NativeBlockValuePicker.DROPDOWN,
        valuePickerOptions = [
            NativeBlockValuePickerOption("clip", "clip"),
            NativeBlockValuePickerOption("ellipsis", "ellipsis"),
            NativeBlockValuePickerOption("visible", "visible")
        ],
        valuePickerGroup = NativeBlockValuePickerPosition("Font")
    ) overflow: String = "clip",
    @NativeBlockProp(
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Font")
    ) minLines: Int = 1,
    @NativeBlockProp(
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Font")
    ) maxLines: Int = 9999,
) {
    val textStyle = typographyBuilder(
        fontFamily = fontFamilyMapper(fontFamily),
        fontWeight = fontWeightMapper(fontWeight),
        fontSize = fontSize.sp
    )
    val modifier = Modifier
        .widthAndHeight(width, height)
    Text(
        modifier = modifier,
        text = text,
        color = Color(textColor.toColorInt()),
        style = textStyle,
        textAlign = textAlignmentMapper(textAlign),
        overflow = textOverflowMapper(overflow),
        minLines = minLines,
        maxLines = maxLines,
    )
}

