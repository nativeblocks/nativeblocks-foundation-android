package io.nativeblocks.foundation.blocks.button

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.nativeblocks.compiler.type.BlockIndex
import io.nativeblocks.compiler.type.NativeBlock
import io.nativeblocks.compiler.type.NativeBlockData
import io.nativeblocks.compiler.type.NativeBlockEvent
import io.nativeblocks.compiler.type.NativeBlockProp
import io.nativeblocks.compiler.type.NativeBlockSlot
import io.nativeblocks.compiler.type.NativeBlockValuePicker
import io.nativeblocks.compiler.type.NativeBlockValuePickerOption
import io.nativeblocks.compiler.type.NativeBlockValuePickerPosition
import io.nativeblocks.core.api.provider.block.BlockProps
import io.nativeblocks.core.api.util.fontFamilyMapper
import io.nativeblocks.foundation.util.blockWeight
import io.nativeblocks.foundation.util.widthAndHeight

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
 * @param weight Specifies the weight of the layout in row or column. Default is 0.0 means not set.
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
    keyType = "nativeblocks/button",
    name = "Native Button",
    description = "Nativeblocks button block",
    version = 1,
    versionName = "1"
)
@Composable
fun NativeButton(
    blockProps: BlockProps? = null,
    @NativeBlockData(
        description = "The text displayed on the button."
    ) text: String,
    @NativeBlockData(
        description = "Whether the button is enabled or not.",
        defaultValue = "true"
    ) enable: Boolean = true,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ],
        description = "The width of the button (e.g., 'match' or 'wrap' or number).",
        defaultValue = "wrap"
    ) width: String = "wrap",
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ],
        description = "The height of the button (e.g., 'match' or 'wrap' or number).",
        defaultValue = "wrap"
    ) height: String = "wrap",
    @NativeBlockProp(
        description = "Specifies the weight of the layout in row or column. Default is 0.0 means not set.",
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        defaultValue = "0F"
    ) weight: Float = 0F,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Content color"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        description = "The color of the button's text or content.",
        defaultValue = "#FFFFFFFF"
    ) contentColor: Color = Color.White,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Content color"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        description = "The color of the button's content when it is disabled.",
        defaultValue = "#FFFFFFB2"
    ) disabledContentColor: Color = Color(0xFFFFFFB2),
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Background"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        description = "The background color of the button.",
        defaultValue = "#FF212121"
    ) backgroundColor: Color = Color(0xFF212121),
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Background"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        description = "The background color when the button is disabled.",
        defaultValue = "#212121B2"
    ) disableBackgroundColor: Color = Color(0x212121B2),
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Padding"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "Padding on the start (left) side in DP.",
        defaultValue = "4.0"
    ) paddingStart: Dp = 4.dp,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Padding"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "Padding on the top side in DP.",
        defaultValue = "4.0"
    ) paddingTop: Dp = 4.dp,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Padding"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "Padding on the end (right) side in DP.",
        defaultValue = "4.0"
    ) paddingEnd: Dp = 4.dp,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Padding"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "Padding on the bottom side in DP.",
        defaultValue = "4.0"
    ) paddingBottom: Dp = 4.dp,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Content padding"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "Content padding on the start (left) side in DP.",
        defaultValue = "4.0"
    ) contentPaddingStart: Dp = 4.dp,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Content padding"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "Content padding on the top side in DP.",
        defaultValue = "4.0"
    ) contentPaddingTop: Dp = 4.dp,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Content padding"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "Content padding on the end (right) side in DP.",
        defaultValue = "4.0"
    ) contentPaddingEnd: Dp = 4.dp,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Content padding"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "Content padding on the bottom side in DP.",
        defaultValue = "4.0"
    ) contentPaddingBottom: Dp = 4.dp,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Border"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        description = "The border color of the button.",
        defaultValue = "#FF212121"
    ) borderColor: Color = Color(0xFF212121),
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Border"),
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        description = "The border color when the button is disabled.",
        defaultValue = "#212121B2"
    ) disableBorderColor: Color = Color(0x212121B2),
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Border"),
        description = "border width of the column in DP.",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        defaultValue = "0"
    ) borderWidth: Dp = 0.dp,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Border"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "The radius for the top-start corner in DP.",
        defaultValue = "4.0"
    ) radiusTopStart: Dp = 4.dp,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Border"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "The radius for the top-end corner in DP.",
        defaultValue = "4.0"
    ) radiusTopEnd: Dp = 4.dp,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Border"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "The radius for the bottom-start corner in DP.",
        defaultValue = "4.0"
    ) radiusBottomStart: Dp = 4.dp,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Border"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "The radius for the bottom-end corner in DP.",
        defaultValue = "4.0"
    ) radiusBottomEnd: Dp = 4.dp,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        description = "The font size of the button text in SP.",
        defaultValue = "14"
    ) fontSize: TextUnit = 14.sp,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Font"),
        description = "The font family used for the button text.",
        defaultValue = "default"
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
        description = "The alignment of the button text (e.g., 'start', 'center').",
        defaultValue = "start"
    ) textAlign: TextAlign = TextAlign.Start,
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
        description = "The font weight for the button text (e.g., 'normal', 'bold').",
        defaultValue = "normal"
    ) fontWeight: FontWeight = FontWeight.Normal,
    @NativeBlockSlot(
        description = "Slot for adding a leading icon to the button."
    ) onLeadingIcon: (@Composable (index: BlockIndex, scope: Any?) -> Unit)? = null,
    @NativeBlockSlot(
        description = "Slot for adding a trailing icon to the button."
    ) onTrailingIcon: (@Composable (index: BlockIndex, scope: Any?) -> Unit)? = null,
    @NativeBlockEvent(
        description = "Callback triggered when the button is clicked."
    ) onClick: () -> Unit,
) {
    val textStyle = TextStyle(
        fontFamily = fontFamilyMapper(fontFamily),
        fontWeight = fontWeight,
        fontSize = fontSize
    )

    val modifier = Modifier
        .widthAndHeight(width, height)
        .padding(
            PaddingValues(
                start = paddingStart,
                top = paddingTop,
                end = paddingEnd,
                bottom = paddingBottom
            )
        )
        .blockWeight(weight, blockProps?.hierarchy?.last()?.scope)

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            disabledBackgroundColor = disableBackgroundColor,
            disabledContentColor = disabledContentColor,
        ),
        modifier = modifier,
        shape = RoundedCornerShape(
            topStart = radiusTopStart,
            topEnd = radiusTopEnd,
            bottomStart = radiusBottomStart,
            bottomEnd = radiusBottomEnd
        ),
        border = BorderStroke(borderWidth, if (enable) borderColor else disableBorderColor),
        enabled = enable
    ) {
        Row(
            modifier = Modifier.padding(
                PaddingValues(
                    start = contentPaddingStart,
                    top = contentPaddingTop,
                    end = contentPaddingEnd,
                    bottom = contentPaddingBottom
                )
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            onLeadingIcon?.let { it(-1, null) }
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(
                modifier = Modifier,
                text = text,
                color = contentColor,
                style = textStyle,
                textAlign = textAlign,
                overflow = TextOverflow.Clip,
                minLines = 1,
                maxLines = 2,
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            onTrailingIcon?.let { it(-1, null) }
        }
    }
}