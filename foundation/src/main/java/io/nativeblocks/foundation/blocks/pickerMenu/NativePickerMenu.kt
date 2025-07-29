package io.nativeblocks.foundation.blocks.pickerMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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
import io.nativeblocks.foundation.util.blockWeight
import io.nativeblocks.foundation.util.shapeMapper
import io.nativeblocks.foundation.util.widthAndHeight

@NativeBlock(
    name = "Native Picker Menu",
    keyType = "nativeblocks/picker_menu",
    description = "A dropdown-style menu that displays a list of selectable items.",
    version = 1,
    versionName = "1.0.0"
)
@Composable
fun NativePickerMenu(
    blockProps: BlockProps? = null,

    @NativeBlockData(
        description = "Total number of items",
        defaultValue = "0"
    )
    length: Int = 0,

    @NativeBlockData(
        description = "Currently selected index",
        defaultValue = "0"
    )
    selectedIndex: Int = 0,

    @NativeBlockData(
        description = "Enable interaction",
        defaultValue = "true"
    )
    enable: Boolean = true,

    @NativeBlockSlot(description = "Visual appearance of each item")
    itemContent: @Composable (BlockIndex) -> Unit,

    @NativeBlockEvent(
        description = "Triggered when user selects",
        dataBinding = ["selectedIndex"]
    )
    onSelect: (Int) -> Unit,

    @NativeBlockProp(
        description = "Picker icon color",
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        valuePickerGroup = NativeBlockValuePickerPosition("Colors"),
        defaultValue = "#CCCCCC"
    )
    pickerIconColor: Color = Color.Gray,

    @NativeBlockProp(
        description = "Disabled icon color",
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        valuePickerGroup = NativeBlockValuePickerPosition("Colors"),
        defaultValue = "#88CCCCCC"
    )
    disablePickerIconColor: Color = Color.Gray.copy(alpha = 0.3f),

    @NativeBlockProp(
        description = "Dropdown width",
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ],
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        defaultValue = "wrap"
    )
    width: String = "wrap",

    @NativeBlockProp(
        description = "Dropdown height",
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ],
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        defaultValue = "wrap"
    )
    height: String = "wrap",

    @NativeBlockProp(
        description = "Weight for layout",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        defaultValue = "0.0f"
    )
    weight: Float = 0f,

    @NativeBlockProp(
        description = "Corner radius",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Border"),
        defaultValue = "8"
    )
    cornerRadius: Dp = 8.dp,

    @NativeBlockProp(
        description = "Background color",
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        valuePickerGroup = NativeBlockValuePickerPosition("Background"),
        defaultValue = "#00000000"
    )
    backgroundColor: Color = Color.Transparent,
    @NativeBlockProp(
        description = "Disabled background color",
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        valuePickerGroup = NativeBlockValuePickerPosition("Background"),
        defaultValue = "#FFCCCCCC"
    )
    disabledBackgroundColor: Color = Color.LightGray,
    @NativeBlockProp(
        description = "Border color",
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        valuePickerGroup = NativeBlockValuePickerPosition("Border"),
        defaultValue = "#888888"
    )
    borderColor: Color = Color.Gray,

    @NativeBlockProp(
        description = "Border width",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Border"),
        defaultValue = "1"
    )
    borderWidth: Dp = 1.dp,

    @NativeBlockProp(
        description = "Top padding",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Padding"),
        defaultValue = "8"
    )
    paddingTop: Dp = 8.dp,

    @NativeBlockProp(
        description = "Start padding",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Padding"),
        defaultValue = "8"
    )
    paddingStart: Dp = 8.dp,

    @NativeBlockProp(
        description = "Bottom padding",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Padding"),
        defaultValue = "8"
    )
    paddingBottom: Dp = 8.dp,

    @NativeBlockProp(
        description = "End padding",
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        valuePickerGroup = NativeBlockValuePickerPosition("Padding"),
        defaultValue = "8"
    )
    paddingEnd: Dp = 8.dp
) {
    var expanded by remember { mutableStateOf(false) }

    val shape = shapeMapper("rectangle", cornerRadius, cornerRadius, cornerRadius, cornerRadius)
    val bgColor = if (enable.not()) disabledBackgroundColor else backgroundColor
    val iconColor = if (enable.not()) disablePickerIconColor else pickerIconColor

    Box(
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
            )
    ) {
        LazyRow(
            modifier = Modifier
                .clickable(
                    enabled = enable,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }) {
                    expanded = true
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            item(key = selectedIndex) {
                itemContent(selectedIndex)
            }
            item(key = "ICON") {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = iconColor
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(bgColor)
        ) {
            repeat(length) { index ->
                DropdownMenuItem(
                    onClick = {
                        onSelect(index)
                        expanded = false
                    }
                ) {
                    itemContent(index)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NativePickerMenuPreview() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    Column {
        Text("$selectedIndex")
        NativePickerMenu(
            length = 3,
            selectedIndex = selectedIndex,
            onSelect = { selectedIndex = it },
            itemContent = { index ->
                Text(
                    text = when (index) {
                        0 -> "🍎 Apple"
                        1 -> "🍌 Banana"
                        2 -> "🍇 Grape"
                        else -> "Unknown"
                    },
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        )
    }
}