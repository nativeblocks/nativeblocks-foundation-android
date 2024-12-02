package io.nativeblocks.foundation.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import coil.compose.AsyncImage
import io.nativeblocks.compiler.type.NativeBlock
import io.nativeblocks.compiler.type.NativeBlockData
import io.nativeblocks.compiler.type.NativeBlockProp
import io.nativeblocks.compiler.type.NativeBlockValuePicker
import io.nativeblocks.compiler.type.NativeBlockValuePickerOption
import io.nativeblocks.compiler.type.NativeBlockValuePickerPosition
import io.nativeblocks.core.util.isHttpUrl
import io.nativeblocks.core.util.scaleTypeMapper
import io.nativeblocks.core.util.shapeMapper
import io.nativeblocks.core.util.widthAndHeight

@NativeBlock(
    keyType = "NATIVE_IMAGE",
    name = "Native Image",
    description = "Nativeblocks image block",
    version = 1
)
@Composable
fun NativeImage(
    @NativeBlockData imageUrl: String,
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
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) radiusTopStart: Double = 0.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) radiusTopEnd: Double = 0.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) radiusBottomStart: Double = 0.0,
    @NativeBlockProp(
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) radiusBottomEnd: Double = 0.0,
    @NativeBlockProp(
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("none", "none"),
            NativeBlockValuePickerOption("crop", "crop"),
            NativeBlockValuePickerOption("inside", "inside"),
            NativeBlockValuePickerOption("fit", "fit"),
            NativeBlockValuePickerOption("fillBounds", "fillBounds"),
            NativeBlockValuePickerOption("fillWidth", "fillWidth"),
            NativeBlockValuePickerOption("fillHeight", "fillHeight")
        ]
    )
    scaleType: String = "none",
    @NativeBlockData contentDescription: String = "",
) {
    val shape = shapeMapper(
        "rectangle",
        radiusTopStart.toString(),
        radiusTopEnd.toString(),
        radiusBottomStart.toString(),
        radiusBottomEnd.toString(),
    )

    if (imageUrl.isHttpUrl()) {
        AsyncImage(
            model = imageUrl.trim(),
            contentDescription = contentDescription,
            contentScale = scaleTypeMapper(scaleType),
            modifier = Modifier
                .widthAndHeight(width, height)
                .clip(shape)
        )
    }
}
