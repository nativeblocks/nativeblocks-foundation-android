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

@NativeBlock(
    keyType = "NATIVE_TEXT_FIELD",
    name = "Native TextField",
    description = "Nativeblocks textField block",
    version = 2
)
@Composable
fun NativeTextField(
    @NativeBlockData text: String,
    @NativeBlockData placeholder: String,
    @NativeBlockData label: String,
    @NativeBlockData enable: Boolean = true,
    @NativeBlockData readOnly: Boolean = false,
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
        valuePickerGroup = NativeBlockValuePickerPosition("Content color"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER
    ) contentColor: String = "#FFFFFFFF",
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Content color"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER
    ) disabledContentColor: String = "#FFFFFFB2",
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Background color"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER
    ) backgroundColor: String = "#FF212121",
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Background color"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER
    ) disableBackgroundColor: String = "#212121B2",
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Border color"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER
    ) borderColor: String = "#FF212121",
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Border color"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER
    ) disableBorderColor: String = "#212121B2",
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Border color"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER
    ) borderFocusColor: String = "#FF212121",

    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) radiusTopStart: Double = 4.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) radiusTopEnd: Double = 4.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) radiusBottomStart: Double = 4.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) radiusBottomEnd: Double = 4.0,

    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Spacing"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) paddingStart: Double = 4.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Spacing"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) paddingTop: Double = 4.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Spacing"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) paddingEnd: Double = 4.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Spacing"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) paddingBottom: Double = 4.0,

    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) fontSize: Double = 14.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
    ) fontFamily: String = "default",
    @NativeBlockProp(
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
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) maxLines: Int = 100,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) letterSpacing: Double = 1.25,

    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Direction"),
        valuePicker = NativeBlockValuePicker.DROPDOWN,
        valuePickerOptions = [
            NativeBlockValuePickerOption("RTL", "RTL"),
            NativeBlockValuePickerOption("LTR", "LTR")
        ]
    ) direction: String = "LTR",
    @NativeBlockProp(
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
        description = "TextField leading icon",
    ) onLeadingIcon: (@Composable (index: BlockIndex) -> Unit)? = null,
    @NativeBlockSlot(
        description = "TextField trailing icon",
    ) onTrailingIcon: (@Composable (index: BlockIndex) -> Unit)? = null,
    @NativeBlockEvent(
        dataBinding = ["text"],
        description = "TextField on click",
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