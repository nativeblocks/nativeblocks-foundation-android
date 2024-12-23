package io.nativeblocks.foundation.textField

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import io.nativeblocks.compiler.type.BlockIndex
import io.nativeblocks.compiler.type.NativeBlock
import io.nativeblocks.compiler.type.NativeBlockData
import io.nativeblocks.compiler.type.NativeBlockEvent
import io.nativeblocks.compiler.type.NativeBlockProp
import io.nativeblocks.compiler.type.NativeBlockSlot
import io.nativeblocks.compiler.type.NativeBlockValuePicker
import io.nativeblocks.compiler.type.NativeBlockValuePickerOption
import io.nativeblocks.compiler.type.NativeBlockValuePickerPosition
import io.nativeblocks.core.util.fontFamilyMapper
import io.nativeblocks.core.util.fontWeightMapper
import io.nativeblocks.core.util.keyboardTypeMapper
import io.nativeblocks.core.util.textAlignmentMapper
import io.nativeblocks.core.util.typographyBuilder
import io.nativeblocks.core.util.widthAndHeight
import kotlin.math.roundToInt

/**
 * A customizable text field block for Nativeblocks.
 *
 * **NativeTextField** is a flexible and customizable text input field that integrates with the Nativeblocks ecosystem.
 * It supports features like customizable placeholder, label, font, alignment, padding, and keyboard options.
 *
 * @param text The initial text content of the text field.
 * @param placeholder The placeholder text displayed when the field is empty.
 * @param label The label displayed above the text field.
 * @param enable Whether the text field is enabled. Default is true.
 * @param readOnly Whether the text field is read-only. Default is false.
 * @param width Specifies the width of the text field (e.g., "match" or "wrap"). Default is "wrap".
 * @param height Specifies the height of the text field (e.g., "match" or "wrap"). Default is "wrap".
 * @param contentColor The color of the text content. Default is white (#FFFFFFFF).
 * @param disabledContentColor The color of the text when disabled. Default is a lighter white (#FFFFFFB2).
 * @param backgroundColor The background color of the text field. Default is dark gray (#FF212121).
 * @param disableBackgroundColor The background color of the text field when disabled. Default is semi-transparent dark gray (#212121B2).
 * @param borderColor The border color of the text field. Default is dark gray (#FF212121).
 * @param disableBorderColor The border color when the text field is disabled. Default is semi-transparent dark gray (#212121B2).
 * @param borderFocusColor The border color when the text field is focused. Default is dark gray (#FF212121).
 * @param radiusTopStart The top-start corner radius in DP. Default is 4.0.
 * @param radiusTopEnd The top-end corner radius in DP. Default is 4.0.
 * @param radiusBottomStart The bottom-start corner radius in DP. Default is 4.0.
 * @param radiusBottomEnd The bottom-end corner radius in DP. Default is 4.0.
 * @param paddingStart The padding on the start (left) side in DP. Default is 4.0.
 * @param paddingTop The padding on the top side in DP. Default is 4.0.
 * @param paddingEnd The padding on the end (right) side in DP. Default is 4.0.
 * @param paddingBottom The padding on the bottom side in DP. Default is 4.0.
 * @param fontSize The font size of the text in SP. Default is 14.0.
 * @param fontFamily The font family used for the text. Default is "default".
 * @param textAlign Specifies the alignment of the text (e.g., "start", "center"). Default is "start".
 * @param fontWeight Specifies the font weight of the text (e.g., "normal", "bold"). Default is "normal".
 * @param maxLines The maximum number of lines allowed for the text. Default is 100.
 * @param letterSpacing The letter spacing for the text in SP. Default is 1.25.
 * @param direction The layout direction of the text field (e.g., "LTR" or "RTL"). Default is "LTR".
 * @param keyboardType The type of keyboard to display (e.g., "text", "number"). Default is "normal".
 * @param onLeadingIcon The leading icon slot, if any.
 * @param onTrailingIcon The trailing icon slot, if any.
 * @param onTextChange Callback triggered when the text content changes.
 */
@NativeBlock(
    keyType = "NATIVE_TEXT_FIELD",
    name = "Native TextField",
    description = "Nativeblocks textField block",
    version = 1
)
@Composable
fun NativeTextField(
    @NativeBlockData(description = "The initial text content of the text field.") text: String,
    @NativeBlockData(description = "The placeholder text displayed when the field is empty.") placeholder: String,
    @NativeBlockData(description = "The label displayed above the text field.") label: String,
    @NativeBlockData(description = "Whether the text field is enabled.") enable: Boolean = true,
    @NativeBlockData(description = "Whether the text field is read-only.") readOnly: Boolean = false,
    @NativeBlockProp(
        description = "Specifies the width of the text field (e.g., 'match' or 'wrap').",
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ]
    ) width: String = "wrap",
    @NativeBlockProp(
        description = "Specifies the height of the text field (e.g., 'match' or 'wrap').",
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ]
    ) height: String = "wrap",
    @NativeBlockProp(
        description = "The color of the text content.",
        valuePickerGroup = NativeBlockValuePickerPosition("Content color"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER
    ) contentColor: String = "#FFFFFFFF",
    @NativeBlockProp(
        description = "The color of the text when disabled.",
        valuePickerGroup = NativeBlockValuePickerPosition("Content color"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER
    ) disabledContentColor: String = "#FFFFFFB2",
    @NativeBlockProp(
        description = "The background color of the text field.",
        valuePickerGroup = NativeBlockValuePickerPosition("Background color"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER
    ) backgroundColor: String = "#FF212121",
    @NativeBlockProp(
        description = "The background color of the text field when disabled.",
        valuePickerGroup = NativeBlockValuePickerPosition("Background color"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER
    ) disableBackgroundColor: String = "#212121B2",
    @NativeBlockProp(
        description = "The border color of the text field.",
        valuePickerGroup = NativeBlockValuePickerPosition("Border color"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER
    ) borderColor: String = "#FF212121",
    @NativeBlockProp(
        description = "The border color of the text field when disabled.",
        valuePickerGroup = NativeBlockValuePickerPosition("Border color"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER
    ) disableBorderColor: String = "#212121B2",
    @NativeBlockProp(
        description = "The border color of the text field when focused.",
        valuePickerGroup = NativeBlockValuePickerPosition("Border color"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER
    ) borderFocusColor: String = "#FF212121",

    @NativeBlockProp(
        description = "The radius for the top-start corner of the text field in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) radiusTopStart: Double = 4.0,
    @NativeBlockProp(
        description = "The radius for the top-end corner of the text field in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) radiusTopEnd: Double = 4.0,
    @NativeBlockProp(
        description = "The radius for the bottom-start corner of the text field in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) radiusBottomStart: Double = 4.0,
    @NativeBlockProp(
        description = "The radius for the bottom-end corner of the text field in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) radiusBottomEnd: Double = 4.0,

    @NativeBlockProp(
        description = "The padding on the start (left) side of the text field in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Spacing"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) paddingStart: Double = 4.0,
    @NativeBlockProp(
        description = "The padding on the top side of the text field in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Spacing"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) paddingTop: Double = 4.0,
    @NativeBlockProp(
        description = "The padding on the end (right) side of the text field in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Spacing"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) paddingEnd: Double = 4.0,
    @NativeBlockProp(
        description = "The padding on the bottom side of the text field in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Spacing"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) paddingBottom: Double = 4.0,

    @NativeBlockProp(
        description = "The font size of the text in SP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) fontSize: Double = 14.0,
    @NativeBlockProp(
        description = "The font family for the text.",
        valuePickerGroup = NativeBlockValuePickerPosition("Font")
    ) fontFamily: String = "default",
    @NativeBlockProp(
        description = "The alignment of the text (e.g., 'start', 'center', 'end', 'justify').",
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        valuePicker = NativeBlockValuePicker.DROPDOWN,
        valuePickerOptions = [
            NativeBlockValuePickerOption("start", "start"),
            NativeBlockValuePickerOption("center", "center"),
            NativeBlockValuePickerOption("end", "end"),
            NativeBlockValuePickerOption("justify", "justify")
        ]
    ) textAlign: String = "start",
    @NativeBlockProp(
        description = "The font weight of the text (e.g., 'normal', 'bold').",
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
    ) fontWeight: String = "normal",

    @NativeBlockProp(
        description = "The maximum number of lines for the text field.",
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) maxLines: Int = 100,
    @NativeBlockProp(
        description = "The letter spacing for the text in SP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) letterSpacing: Double = 1.25,

    @NativeBlockProp(
        description = "The layout direction of the text field (e.g., 'LTR', 'RTL').",
        valuePickerGroup = NativeBlockValuePickerPosition("Direction"),
        valuePicker = NativeBlockValuePicker.DROPDOWN,
        valuePickerOptions = [
            NativeBlockValuePickerOption("RTL", "RTL"),
            NativeBlockValuePickerOption("LTR", "LTR")
        ]
    ) direction: String = "LTR",
    @NativeBlockProp(
        description = "The type of keyboard to display for the text field (e.g., 'text', 'number', 'email').",
        valuePickerGroup = NativeBlockValuePickerPosition("Keyboard"),
        valuePicker = NativeBlockValuePicker.DROPDOWN,
        valuePickerOptions = [
            NativeBlockValuePickerOption("text", "text"),
            NativeBlockValuePickerOption("ascii", "ascii"),
            NativeBlockValuePickerOption("number", "number"),
            NativeBlockValuePickerOption("phone", "phone"),
            NativeBlockValuePickerOption("uri", "uri"),
            NativeBlockValuePickerOption("email", "email"),
            NativeBlockValuePickerOption("password", "password"),
            NativeBlockValuePickerOption("numberPassword", "numberPassword"),
            NativeBlockValuePickerOption("decimal", "decimal")
        ]
    ) keyboardType: String = "normal",

    @NativeBlockSlot(
        description = "Defines the leading icon for the text field."
    ) onLeadingIcon: (@Composable (index: BlockIndex) -> Unit)? = null,
    @NativeBlockSlot(
        description = "Defines the trailing icon for the text field."
    ) onTrailingIcon: (@Composable (index: BlockIndex) -> Unit)? = null,
    @NativeBlockEvent(
        description = "Callback triggered when the text field value changes.",
        dataBinding = ["text"]
    ) onTextChange: (String) -> Unit,
) {
    val valueState = remember("") { mutableStateOf(TextFieldValue(text = text)) }
    val focusManager = LocalFocusManager.current

    val textStyle = typographyBuilder(
        fontFamily = fontFamilyMapper(fontFamily),
        fontWeight = fontWeightMapper(fontWeight),
        fontSize = fontSize.sp,
    )

    val modifier = Modifier
        .widthAndHeight(width, height)
        .padding(
            PaddingValues(
                start = paddingStart.dp,
                top = paddingTop.dp,
                end = paddingEnd.dp,
                bottom = paddingBottom.dp
            )
        )
        .focusable()

    val blockDirection = if (direction == "RTL") LocalLayoutDirection provides LayoutDirection.Rtl
    else LocalLayoutDirection provides LayoutDirection.Ltr

    CompositionLocalProvider(blockDirection) {
        OutlinedTextField(
            modifier = modifier,
            value = valueState.value,
            onValueChange = {
                valueState.value = it
                onTextChange.invoke(it.text)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardTypeMapper(keyboardType)
            ),
            keyboardActions = KeyboardActions(onAny = {
                focusManager.clearFocus()
            }),
            textStyle = textStyle.copy(
                letterSpacing = letterSpacing.roundToInt().sp,
                textAlign = textAlignmentMapper(textAlign)
            ),
            shape = RoundedCornerShape(
                topStart = radiusTopStart.dp,
                topEnd = radiusTopEnd.dp,
                bottomStart = radiusBottomStart.dp,
                bottomEnd = radiusBottomEnd.dp
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color(contentColor.toColorInt()),
                disabledTextColor = Color(disabledContentColor.toColorInt()),
                backgroundColor = Color(backgroundColor.toColorInt()),
                unfocusedBorderColor = Color(borderColor.toColorInt()),
                focusedBorderColor = Color(borderFocusColor.toColorInt()),
                disabledBorderColor = Color(disableBorderColor.toColorInt()),
                cursorColor = Color(contentColor.toColorInt())
            ),
            readOnly = readOnly,
            enabled = enable,
            leadingIcon = if (onLeadingIcon != null) {
                { onLeadingIcon.invoke(-1) }
            } else {
                null
            },
            trailingIcon = if (onTrailingIcon != null) {
                { onTrailingIcon.invoke(-1) }
            } else {
                null
            },
            singleLine = maxLines == 1,
            maxLines = maxLines,
            placeholder = {
                Text(
                    text = placeholder,
                    modifier = Modifier,
                    color = Color(contentColor.toColorInt()).copy(alpha = 0.7f),
                    fontSize = textStyle.fontSize,
                    fontStyle = textStyle.fontStyle,
                    fontWeight = textStyle.fontWeight,
                    fontFamily = textStyle.fontFamily,
                    textAlign = textAlignmentMapper(textAlign),
                    letterSpacing = letterSpacing.roundToInt().sp
                )
            },
            label = {
                Text(
                    text = label,
                    modifier = Modifier,
                    color = Color(contentColor.toColorInt()),
                    fontSize = (fontSize - 2).sp,
                    fontFamily = textStyle.fontFamily,
                    textAlign = textAlignmentMapper(textAlign),
                )
            },
        )
    }
}