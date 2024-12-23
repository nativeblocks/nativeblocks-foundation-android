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

/**
 * A customizable button block for Nativeblocks.
 *
 * This block provides flexibility for text styling, colors, spacing, radius, and event handling.
 * It can be used as a reusable button component in server-driven UI designs.
 *
 * @param text The text displayed on the button.
 * @param enable Whether the button is enabled. Default is true.
 * @param width The width of the button (e.g., "match" or "wrap"). Default is "wrap".
 * @param height The height of the button (e.g., "match" or "wrap"). Default is "wrap".
 * @param contentColor The color of the button's text/content. Default is white (#FFFFFFFF).
 * @param disabledContentColor The color of the content when the button is disabled. Default is #FFFFFFB2.
 * @param backgroundColor The background color of the button. Default is #FF212121.
 * @param disableBackgroundColor The background color when the button is disabled. Default is #212121B2.
 * @param borderColor The border color of the button. Default is #FF212121.
 * @param disableBorderColor The border color when the button is disabled. Default is #212121B2.
 * @param paddingStart Padding on the start (left) side in DP. Default is 4.0.
 * @param paddingTop Padding on the top side in DP. Default is 4.0.
 * @param paddingEnd Padding on the end (right) side in DP. Default is 4.0.
 * @param paddingBottom Padding on the bottom side in DP. Default is 4.0.
 * @param contentPaddingStart Content padding on the start (left) side in DP. Default is 4.0.
 * @param contentPaddingTop Content padding on the top side in DP. Default is 4.0.
 * @param contentPaddingEnd Content padding on the end (right) side in DP. Default is 4.0.
 * @param contentPaddingBottom Content padding on the bottom side in DP. Default is 4.0.
 * @param radiusTopStart The radius for the top-start corner in DP. Default is 4.0.
 * @param radiusTopEnd The radius for the top-end corner in DP. Default is 4.0.
 * @param radiusBottomStart The radius for the bottom-start corner in DP. Default is 4.0.
 * @param radiusBottomEnd The radius for the bottom-end corner in DP. Default is 4.0.
 * @param fontSize The font size for the button text in SP. Default is 14.0.
 * @param fontFamily The font family for the button text. Default is "default".
 * @param textAlign The alignment of the button text (e.g., "start", "center"). Default is "start".
 * @param fontWeight The font weight for the button text (e.g., "normal", "bold"). Default is "normal".
 * @param onLeadingIcon Optional slot for a leading icon on the button.
 * @param onTrailingIcon Optional slot for a trailing icon on the button.
 * @param onClick The callback triggered when the button is clicked.
 */
@NativeBlock(
    keyType = "NATIVE_BUTTON",
    name = "Native Button",
    description = "Nativeblocks button block",
    version = 1
)
@Composable
fun NativeButton(
    @NativeBlockData(
        description = "The text displayed on the button."
    ) text: String,
    @NativeBlockData(
        description = "Whether the button is enabled or not."
    ) enable: Boolean = true,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ],
        description = "The width of the button (e.g., 'match' or 'wrap')."
    ) width: String = "wrap",
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ],
        description = "The height of the button (e.g., 'match' or 'wrap')."
    ) height: String = "wrap",
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Content color"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        description = "The color of the button's text or content."
    ) contentColor: String = "#FFFFFFFF",
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Content color"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        description = "The color of the button's content when it is disabled."
    ) disabledContentColor: String = "#FFFFFFB2",
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Background color"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        description = "The background color of the button."
    ) backgroundColor: String = "#FF212121",
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Background color"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        description = "The background color when the button is disabled."
    ) disableBackgroundColor: String = "#212121B2",
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Border color"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        description = "The border color of the button."
    ) borderColor: String = "#FF212121",
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Border color"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        description = "The border color when the button is disabled."
    ) disableBorderColor: String = "#212121B2",
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Spacing"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "Padding on the start (left) side in DP."
    ) paddingStart: Double = 4.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Spacing"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "Padding on the top side in DP."
    ) paddingTop: Double = 4.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Spacing"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "Padding on the end (right) side in DP."
    ) paddingEnd: Double = 4.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Spacing"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "Padding on the bottom side in DP."
    ) paddingBottom: Double = 4.0,

    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Content spacing"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "Content padding on the start (left) side in DP."
    ) contentPaddingStart: Double = 4.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Content spacing"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "Content padding on the top side in DP."
    ) contentPaddingTop: Double = 4.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Content spacing"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "Content padding on the end (right) side in DP."
    ) contentPaddingEnd: Double = 4.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Content spacing"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "Content padding on the bottom side in DP."
    ) contentPaddingBottom: Double = 4.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "The radius for the top-start corner in DP."
    ) radiusTopStart: Double = 4.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "The radius for the top-end corner in DP."
    ) radiusTopEnd: Double = 4.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "The radius for the bottom-start corner in DP."
    ) radiusBottomStart: Double = 4.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "The radius for the bottom-end corner in DP."
    ) radiusBottomEnd: Double = 4.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "The font size of the button text in SP."
    ) fontSize: Double = 14.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        description = "The font family used for the button text."
    ) fontFamily: String = "default",
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        valuePicker = NativeBlockValuePicker.DROPDOWN,
        valuePickerOptions = [
            NativeBlockValuePickerOption("start", "start"),
            NativeBlockValuePickerOption("center", "center"),
            NativeBlockValuePickerOption("end", "end"),
            NativeBlockValuePickerOption("justify", "justify")
        ],
        description = "The alignment of the button text (e.g., 'start', 'center')."
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
        ],
        description = "The font weight for the button text (e.g., 'normal', 'bold')."
    ) fontWeight: String = "normal",
    @NativeBlockSlot(
        description = "Slot for adding a leading icon to the button."
    ) onLeadingIcon: (@Composable (index: BlockIndex) -> Unit)? = null,
    @NativeBlockSlot(
        description = "Slot for adding a trailing icon to the button."
    ) onTrailingIcon: (@Composable (index: BlockIndex) -> Unit)? = null,
    @NativeBlockEvent(
        description = "Callback triggered when the button is clicked."
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