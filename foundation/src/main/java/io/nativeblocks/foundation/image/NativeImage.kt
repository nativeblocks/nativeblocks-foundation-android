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

/**
 * A composable block for displaying images with customizable properties such as size, corner radii, and scaling.
 *
 * This block supports dynamic configuration for server-driven UI, making it versatile for various use cases.
 *
 * @param imageUrl The URL of the image to display. Must be a valid HTTP or HTTPS URL.
 * @param width The width of the image (e.g., "match" or "wrap"). Default is "wrap".
 * @param height The height of the image (e.g., "match" or "wrap"). Default is "wrap".
 * @param radiusTopStart The radius for the top-start corner in DP. Default is 0.0.
 * @param radiusTopEnd The radius for the top-end corner in DP. Default is 0.0.
 * @param radiusBottomStart The radius for the bottom-start corner in DP. Default is 0.0.
 * @param radiusBottomEnd The radius for the bottom-end corner in DP. Default is 0.0.
 * @param scaleType The scaling strategy for the image (e.g., "fit", "crop"). Default is "none".
 * @param contentDescription A description of the image content for accessibility purposes. Default is an empty string.
 */
@NativeBlock(
    keyType = "NATIVE_IMAGE",
    name = "Native Image",
    description = "Nativeblocks image block",
    version = 1
)
@Composable
fun NativeImage(
    @NativeBlockData(
        description = "The URL of the image to display. Must be a valid HTTP or HTTPS URL."
    ) imageUrl: String,
    @NativeBlockProp(
        description = "The width of the image (e.g., 'match' or 'wrap').",
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ]
    ) width: String = "wrap",
    @NativeBlockProp(
        description = "The height of the image (e.g., 'match' or 'wrap').",
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ]
    ) height: String = "wrap",
    @NativeBlockProp(
        description = "The radius for the top-start corner in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) radiusTopStart: Double = 0.0,
    @NativeBlockProp(
        description = "The radius for the top-end corner in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) radiusTopEnd: Double = 0.0,
    @NativeBlockProp(
        description = "The radius for the bottom-start corner in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) radiusBottomStart: Double = 0.0,
    @NativeBlockProp(
        description = "The radius for the bottom-end corner in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT
    ) radiusBottomEnd: Double = 0.0,
    @NativeBlockProp(
        description = "The scaling strategy for the image (e.g., 'fit', 'crop').",
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
    ) scaleType: String = "none",
    @NativeBlockData(
        description = "A description of the image content for accessibility purposes."
    ) contentDescription: String = "",
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
