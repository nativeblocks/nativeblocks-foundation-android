package io.nativeblocks.foundation.blocks.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import io.nativeblocks.compiler.type.NativeBlock
import io.nativeblocks.compiler.type.NativeBlockData
import io.nativeblocks.compiler.type.NativeBlockProp
import io.nativeblocks.compiler.type.NativeBlockValuePicker
import io.nativeblocks.compiler.type.NativeBlockValuePickerOption
import io.nativeblocks.compiler.type.NativeBlockValuePickerPosition
import io.nativeblocks.core.util.fontFamilyMapper
import io.nativeblocks.foundation.util.widthAndHeight

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
    version = 2
)
@Composable
fun NativeText(
    @NativeBlockData(description = "The text content to be displayed.")
    text: String,

    @NativeBlockProp(
        description = "The width of the text block (e.g., 'match' or 'wrap').",
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ],
        defaultValue = "wrap"
    )
    width: String = "wrap",
    @NativeBlockProp(
        description = "The height of the text block (e.g., 'match' or 'wrap').",
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ],
        defaultValue = "wrap"
    )
    height: String = "wrap",
    @NativeBlockProp(
        description = "The font family for the text.",
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        defaultValue = "default"
    )
    fontFamily: String = "default",
    @NativeBlockProp(
        description = "The font size for the text in SP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        defaultValue = "14"
    ) fontSize: TextUnit = 14.sp,
    @NativeBlockProp(
        description = "The color of the text in hexadecimal format.",
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        defaultValue = "#ffffffff"
    )
    textColor: Color = Color.White,
    @NativeBlockProp(
        description = "The alignment of the text (e.g., 'start', 'center').",
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        valuePicker = NativeBlockValuePicker.DROPDOWN,
        valuePickerOptions = [
            NativeBlockValuePickerOption("start", "start"),
            NativeBlockValuePickerOption("center", "center"),
            NativeBlockValuePickerOption("end", "end"),
            NativeBlockValuePickerOption("justify", "justify")
        ],
        defaultValue = "start"
    ) textAlign: TextAlign = TextAlign.Start,

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
        ],
        defaultValue = "normal"
    ) fontWeight: FontWeight = FontWeight.Normal,
    @NativeBlockProp(
        description = "The overflow behavior of the text (e.g., 'clip', 'ellipsis').",
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        valuePicker = NativeBlockValuePicker.DROPDOWN,
        valuePickerOptions = [
            NativeBlockValuePickerOption("clip", "clip"),
            NativeBlockValuePickerOption("ellipsis", "ellipsis"),
            NativeBlockValuePickerOption("visible", "visible")
        ],
        defaultValue = "clip"
    )
    overflow: TextOverflow = TextOverflow.Clip,
    @NativeBlockProp(
        description = "The minimum number of lines to display.",
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        defaultValue = "1"
    )
    minLines: Int = 1,
    @NativeBlockProp(
        description = "The maximum number of lines to display.",
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        defaultValue = "9999"
    )
    maxLines: Int = 9999,
) {
    val textStyle = TextStyle(
        fontFamily = fontFamilyMapper(fontFamily),
        fontWeight = fontWeight,
        fontSize = fontSize
    )
    val modifier = Modifier
        .widthAndHeight(width, height)
    Text(
        modifier = modifier,
        text = text,
        color = textColor,
        style = textStyle,
        textAlign = textAlign,
        overflow = overflow,
        minLines = minLines,
        maxLines = maxLines,
    )
}

