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

/**
 * A composable block for displaying customizable text with support for font styling, alignment, color,
 * and text overflow properties.
 *
 * This block integrates seamlessly into the Nativeblocks ecosystem, offering dynamic configuration of
 * text properties for server-driven UI.
 *
 * @param text The text content to display.
 * @param width The width of the text block (e.g., "match" or "wrap"). Default is "wrap".
 * @param height The height of the text block (e.g., "match" or "wrap"). Default is "wrap".
 * @param fontFamily The font family for the text. Default is "default".
 * @param fontSize The font size for the text in SP. Default is 14.0.
 * @param textColor The color of the text in hexadecimal format. Default is "#ffffffff".
 * @param textAlign The alignment of the text (e.g., "start", "center"). Default is "start".
 * @param fontWeight The weight of the font for the text (e.g., "normal", "bold"). Default is "normal".
 * @param overflow The overflow behavior of the text (e.g., "clip", "ellipsis"). Default is "clip".
 * @param minLines The minimum number of lines to display. Default is 1.
 * @param maxLines The maximum number of lines to display. Default is 9999.
 */
@NativeBlock(
    keyType = "NATIVE_TEXT",
    name = "Native Text",
    description = "Nativeblocks text block",
    version = 1
)
@Composable
fun NativeText(
    @NativeBlockData
    text: String,

    @NativeBlockProp(
        description = "The width of the text block (e.g., 'match' or 'wrap').",
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ]
    )
    width: String = "wrap",
    @NativeBlockProp(
        description = "The height of the text block (e.g., 'match' or 'wrap').",
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ]
    )
    height: String = "wrap",
    @NativeBlockProp(
        description = "The font family for the text.",
        valuePickerGroup = NativeBlockValuePickerPosition("Font")
    )
    fontFamily: String = "default",
    @NativeBlockProp(
        description = "The font size for the text in SP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    )
    fontSize: Double = 14.0,
    @NativeBlockProp(
        description = "The color of the text in hexadecimal format.",
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER
    )
    textColor: String = "#ffffffff",
    @NativeBlockProp(
        description = "The alignment of the text (e.g., 'start', 'center').",
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        valuePicker = NativeBlockValuePicker.DROPDOWN,
        valuePickerOptions = [
            NativeBlockValuePickerOption("start", "start"),
            NativeBlockValuePickerOption("center", "center"),
            NativeBlockValuePickerOption("end", "end"),
            NativeBlockValuePickerOption("justify", "justify")
        ]
    )
    textAlign: String = "start",

    @NativeBlockProp(
        description = "The weight of the font for the text (e.g., 'normal', 'bold').",
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
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
        ]
    )
    fontWeight: String = "normal",
    @NativeBlockProp(
        description = "The overflow behavior of the text (e.g., 'clip', 'ellipsis').",
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        valuePicker = NativeBlockValuePicker.DROPDOWN,
        valuePickerOptions = [
            NativeBlockValuePickerOption("clip", "clip"),
            NativeBlockValuePickerOption("ellipsis", "ellipsis"),
            NativeBlockValuePickerOption("visible", "visible")
        ]
    )
    overflow: String = "clip",
    @NativeBlockProp(
        description = "The minimum number of lines to display.",
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    )
    minLines: Int = 1,
    @NativeBlockProp(
        description = "The maximum number of lines to display.",
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    )
    maxLines: Int = 9999,
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

