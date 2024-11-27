package io.nativeblocks.foundation.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
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
import io.nativeblocks.core.util.textAlignmentMapper
import io.nativeblocks.core.util.typographyBuilder
import io.nativeblocks.core.util.widthAndHeight

@NativeBlock(
    keyType = "NATIVE_BUTTON",
    name = "Native Button",
    description = "Nativeblocks button block",
    version = 2
)
@Composable
fun NativeButton(
    @NativeBlockData text: String,
    @NativeBlockData enable: Boolean = true,
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
        valuePickerGroup = NativeBlockValuePickerPosition("Content spacing"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) contentPaddingStart: Double = 4.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Content spacing"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) contentPaddingTop: Double = 4.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Content spacing"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) contentPaddingEnd: Double = 4.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Content spacing"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) contentPaddingBottom: Double = 4.0,
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
    @NativeBlockSlot(
        description = "Button leading icon",
    ) onLeadingIcon: (@Composable (index: BlockIndex) -> Unit)? = null,
    @NativeBlockSlot(
        description = "Button trailing icon",
    ) onTrailingIcon: (@Composable (index: BlockIndex) -> Unit)? = null,
    @NativeBlockEvent(
        description = "Button on click",
    ) onClick: () -> Unit,
) {
    val textStyle = typographyBuilder(
        fontFamily = fontFamilyMapper(fontFamily),
        fontWeight = fontWeightMapper(fontWeight),
        fontSize = fontSize.sp
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

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(backgroundColor.toColorInt()),
            contentColor = Color(contentColor.toColorInt()),
            disabledBackgroundColor = Color(disableBackgroundColor.toColorInt()),
            disabledContentColor = Color(disabledContentColor.toColorInt()),
        ),
        modifier = modifier,
        shape = RoundedCornerShape(
            topStart = radiusTopStart.dp,
            topEnd = radiusTopEnd.dp,
            bottomStart = radiusBottomStart.dp,
            bottomEnd = radiusBottomEnd.dp
        ),
        border = BorderStroke(
            1.dp,
            Color(if (enable) borderColor.toColorInt() else disableBorderColor.toColorInt())
        ),
        enabled = enable
    ) {
        Row(
            modifier = Modifier.padding(
                PaddingValues(
                    start = contentPaddingStart.dp,
                    top = contentPaddingTop.dp,
                    end = contentPaddingEnd.dp,
                    bottom = contentPaddingBottom.dp
                )
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            onLeadingIcon?.let { it(-1) }
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(
                modifier = Modifier,
                text = text,
                color = Color(contentColor.toColorInt()),
                style = textStyle,
                textAlign = textAlignmentMapper(textAlign),
                overflow = TextOverflow.Clip,
                minLines = 1,
                maxLines = 9999,
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            onTrailingIcon?.let { it(-1) }
        }
    }
}