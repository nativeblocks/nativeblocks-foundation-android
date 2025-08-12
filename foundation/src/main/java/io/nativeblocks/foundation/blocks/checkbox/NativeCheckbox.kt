package io.nativeblocks.foundation.blocks.checkbox

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.nativeblocks.compiler.type.NativeBlock
import io.nativeblocks.compiler.type.NativeBlockData
import io.nativeblocks.compiler.type.NativeBlockEvent
import io.nativeblocks.compiler.type.NativeBlockProp
import io.nativeblocks.compiler.type.NativeBlockValuePicker
import io.nativeblocks.compiler.type.NativeBlockValuePickerPosition

@NativeBlock(
    name = "Native Checkbox",
    keyType = "nativeblocks/checkbox",
    description = "A simple checkbox component for Nativeblocks.",
    version = 1,
    versionName = "1.0.0"
)
@Composable
fun NativeCheckbox(
    @NativeBlockData(
        description = "Enable or disable the checkbox.",
        defaultValue = "true"
    )
    enable: Boolean = true,

    @NativeBlockData(
        description = "Initial checked state.",
        defaultValue = "false"
    )
    isChecked: Boolean = false,

    @NativeBlockEvent(
        description = "Called when checkbox value changes.",
        dataBinding = ["isChecked"]
    )
    onChange: (Boolean) -> Unit,

    @NativeBlockProp(
        description = "Color when checked. Example: #007AFF",
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        valuePickerGroup = NativeBlockValuePickerPosition("Check"),
        defaultValue = "#007AFF"
    )
    checkedColor: Color = Color(0xFF007AFF),

    @NativeBlockProp(
        description = "Color when unchecked. Example: #888888",
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        valuePickerGroup = NativeBlockValuePickerPosition("Check"),
        defaultValue = "#FF888888"
    )
    uncheckedColor: Color = Color.Gray,

    @NativeBlockProp(
        description = "Color of the checkmark icon. Example: #FFFFFF",
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        valuePickerGroup = NativeBlockValuePickerPosition("Check"),
        defaultValue = "#FFFFFF"
    )
    checkmarkColor: Color = Color.White,

    @NativeBlockProp(
        description = "Color when disabled. Example: #CCCCCC",
        valuePicker = NativeBlockValuePicker.COLOR_PICKER,
        valuePickerGroup = NativeBlockValuePickerPosition("Check"),
        defaultValue = "#FFCCCCCC"
    )
    disabledColor: Color = Color.LightGray,

    // Padding props
    @NativeBlockProp(
        description = "Top padding (dp).",
        valuePickerGroup = NativeBlockValuePickerPosition("Padding"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        defaultValue = "8"
    )
    paddingTop: Dp = 8.dp,

    @NativeBlockProp(
        description = "Start (leading) padding (dp).",
        valuePickerGroup = NativeBlockValuePickerPosition("Padding"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        defaultValue = "8"
    )
    paddingStart: Dp = 8.dp,

    @NativeBlockProp(
        description = "Bottom padding (dp).",
        valuePickerGroup = NativeBlockValuePickerPosition("Padding"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        defaultValue = "8"
    )
    paddingBottom: Dp = 8.dp,

    @NativeBlockProp(
        description = "End (trailing) padding (dp).",
        valuePickerGroup = NativeBlockValuePickerPosition("Padding"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        defaultValue = "8"
    )
    paddingEnd: Dp = 8.dp,
) {

    val rowModifier = Modifier
        .padding(start = paddingStart, top = paddingTop, end = paddingEnd, bottom = paddingBottom)
    Checkbox(
        modifier = rowModifier,
        checked = isChecked,
        onCheckedChange = {
            if (enable) {
                onChange(it)
            }
        },
        enabled = enable,
        colors = CheckboxDefaults.colors(
            checkedColor = checkedColor,
            uncheckedColor = uncheckedColor,
            checkmarkColor = checkmarkColor,
            disabledColor = disabledColor
        )
    )
}

@Preview(showBackground = true)
@Composable
fun NativeCheckboxPreview() {
    var isChecked by remember { mutableStateOf(false) }

    NativeCheckbox(
        isChecked = isChecked,
        onChange = { isChecked = it },
    )
}
