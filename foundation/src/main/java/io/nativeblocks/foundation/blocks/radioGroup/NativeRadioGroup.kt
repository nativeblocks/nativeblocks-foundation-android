package io.nativeblocks.foundation.blocks.radioGroup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
import io.nativeblocks.foundation.util.blockWeight
import io.nativeblocks.foundation.util.shapeMapper
import io.nativeblocks.foundation.util.widthAndHeight

@NativeBlock(
    name = "Native Radio Group",
    keyType = "nativeblocks/radio_group",
    description = "Displays a list of radio options. Only one item can be selected at a time.",
    version = 1,
    versionName = "1.0.0"
)
@Composable
fun NativeRadioGroup(
    blockProps: BlockProps? = null,

    @NativeBlockData(
        description = "Specifies the number of items to display in the radio group.",
        defaultValue = "0"
    ) length: Int = 0,

    @NativeBlockData(
        description = "The index of the currently selected item.",
        defaultValue = "0"
    ) selectedIndex: Int = 0,

    @NativeBlockData(
        description = "When false, the layout is disabled and does not respond to user interaction.",
        defaultValue = "true"
    ) enable: Boolean = true,

    @NativeBlockEvent(
        description = "Triggered when the user selects a new option.",
        dataBinding = ["selectedIndex"]
    ) onSelect: (Int) -> Unit,

    @NativeBlockSlot(
        description = "Defines the visual appearance of each item. Can include text, icons, or custom views."
    ) itemContent: @Composable (index: BlockIndex) -> Unit,

    @NativeBlockProp(
        description = "The space between the selection circle and the item content.",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Spacing"),
        defaultValue = "8"
    ) horizontalSpacing: Dp = 8.dp,

    @NativeBlockProp(
        description = "The vertical space between items in the list.",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Spacing"),
        defaultValue = "8"
    ) verticalSpacing: Dp = 8.dp,

    @NativeBlockProp(
        description = "Defines the width of the component.",
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ],
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        defaultValue = "wrap"
    ) width: String = "wrap",

    @NativeBlockProp(
        description = "Defines the height of the component.",
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ],
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        defaultValue = "wrap"
    ) height: String = "wrap",

    @NativeBlockProp(
        description = "A layout weight that determines how much space this view should occupy relative to siblings.",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        defaultValue = "0.0f"
    ) weight: Float = 0f,

    @NativeBlockProp(
        description = "Horizontal alignment of items.",
        valuePicker = NativeBlockValuePicker.DROPDOWN,
        valuePickerOptions = [
            NativeBlockValuePickerOption("start", "Start"),
            NativeBlockValuePickerOption("center", "Center"),
            NativeBlockValuePickerOption("end", "End")
        ],
        valuePickerGroup = NativeBlockValuePickerPosition("Alignment"),
        defaultValue = "start"
    ) horizontalAlignment: Alignment.Horizontal = Alignment.Start,

    @NativeBlockProp(
        description = "Padding top values",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Padding"),
        defaultValue = "8"
    ) paddingTop: Dp = 8.dp,
    @NativeBlockProp(
        description = "Padding start values",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Padding"),
        defaultValue = "8"
    )
    paddingStart: Dp = 8.dp,
    @NativeBlockProp(
        description = "Padding bottom values",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Padding"),
        defaultValue = "8"
    )
    paddingBottom: Dp = 8.dp,
    @NativeBlockProp(
        description = "Padding end values",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Padding"),
        defaultValue = "8"
    )
    paddingEnd: Dp = 8.dp,

    @NativeBlockProp(
        description = "Background color.",
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        valuePickerGroup = NativeBlockValuePickerPosition("Background"),
        defaultValue = "#00000000"
    ) backgroundColor: Color = Color.Transparent,

    @NativeBlockProp(
        description = "Background color when disabled.",
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        valuePickerGroup = NativeBlockValuePickerPosition("Background"),
        defaultValue = "#FFCCCCCC"
    ) disabledBackgroundColor: Color = Color.LightGray,

    @NativeBlockProp(
        description = "Selected color.",
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        valuePickerGroup = NativeBlockValuePickerPosition("State"),
        defaultValue = "#FF0000FF"
    ) selectedColor: Color = Color.Blue,

    @NativeBlockProp(
        description = "Unselected color.",
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        valuePickerGroup = NativeBlockValuePickerPosition("State"),
        defaultValue = "#FF0000FF"
    ) unselectedColor: Color = Color.Blue,

    @NativeBlockProp(
        description = "Color when disabled.",
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        valuePickerGroup = NativeBlockValuePickerPosition("State"),
        defaultValue = "#FF888888"
    ) disabledColor: Color = Color.Gray,

    @NativeBlockProp(
        description = "Corner radius.",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Border"),
        defaultValue = "8"
    ) cornerRadius: Dp = 8.dp,

    @NativeBlockProp(
        description = "Border color.",
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        valuePickerGroup = NativeBlockValuePickerPosition("Border"),
        defaultValue = "#FF888888"
    ) borderColor: Color = Color.Gray,

    @NativeBlockProp(
        description = "Border width.",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Border"),
        defaultValue = "1"
    ) borderWidth: Dp = 1.dp
) {
    val shape = shapeMapper("rectangle", cornerRadius, cornerRadius, cornerRadius, cornerRadius)
    val bgColor = if (enable.not()) disabledBackgroundColor else backgroundColor

    Column(
        modifier = Modifier
            .widthAndHeight(width, height)
            .blockWeight(weight, blockProps?.hierarchy?.last()?.scope)
            .background(bgColor, shape)
            .border(borderWidth, borderColor, shape)
            .padding(
                start = paddingStart,
                top = paddingTop,
                end = paddingEnd,
                bottom = paddingBottom
            ),
        verticalArrangement = Arrangement.spacedBy(verticalSpacing),
        horizontalAlignment = horizontalAlignment
    ) {
        repeat(length) { index ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
                modifier = Modifier
                    .clickable(
                        enabled = enable,
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onSelect.invoke(index) }
            ) {
                RadioButton(
                    selected = index == selectedIndex,
                    onClick = null,
                    enabled = enable,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = selectedColor,
                        unselectedColor = unselectedColor,
                        disabledColor = disabledColor,
                    )
                )
                itemContent(index)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NativeRadioGroupPreview() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    Column {
        Text("$selectedIndex")
        NativeRadioGroup(
            length = 3,
            selectedIndex = selectedIndex,
            onSelect = { index ->
                selectedIndex = index
            },
            itemContent = { index ->
                Text("item $index")
            }
        )
    }
}
