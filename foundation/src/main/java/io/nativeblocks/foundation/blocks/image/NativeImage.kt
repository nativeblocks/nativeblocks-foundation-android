package io.nativeblocks.foundation.blocks.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.nativeblocks.compiler.type.NativeBlock
import io.nativeblocks.compiler.type.NativeBlockData
import io.nativeblocks.compiler.type.NativeBlockProp
import io.nativeblocks.compiler.type.NativeBlockValuePicker
import io.nativeblocks.compiler.type.NativeBlockValuePickerOption
import io.nativeblocks.compiler.type.NativeBlockValuePickerPosition
import io.nativeblocks.core.api.provider.block.BlockProps
import io.nativeblocks.foundation.util.blockWeight
import io.nativeblocks.foundation.util.isHttpUrl
import io.nativeblocks.foundation.util.shapeMapper
import io.nativeblocks.foundation.util.widthAndHeight

/**
 * A composable block for displaying images with customizable properties such as size, corner radii, and scaling.
 *
 * This block supports dynamic configuration for server-driven UI, making it versatile for various use cases.
 *
 * @param imageUrl The URL of the image to display. Must be a valid HTTP or HTTPS URL.
 * @param width The width of the image (e.g., "match" or "wrap"). Default is "wrap".
 * @param height The height of the image (e.g., "match" or "wrap"). Default is "wrap".
 * @param weight Specifies the weight of the layout in row or column. Default is 0.0 means not set..
 * @param radiusTopStart The radius for the top-start corner in DP. Default is 0.0.
 * @param radiusTopEnd The radius for the top-end corner in DP. Default is 0.0.
 * @param radiusBottomStart The radius for the bottom-start corner in DP. Default is 0.0.
 * @param radiusBottomEnd The radius for the bottom-end corner in DP. Default is 0.0.
 * @param scaleType The scaling strategy for the image (e.g., "fit", "crop"). Default is "none".
 * @param contentDescription A description of the image content for accessibility purposes. Default is an empty string.
 */
@NativeBlock(
    keyType = "nativeblocks/image",
    name = "Native Image",
    description = "Nativeblocks image block",
    version = 1,
    versionName = "1"
)
@Composable
fun NativeImage(
    blockProps: BlockProps? = null,
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
        ],
        defaultValue = "wrap"
    ) width: String = "wrap",
    @NativeBlockProp(
        description = "The height of the image (e.g., 'match' or 'wrap').",
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.COMBOBOX_INPUT,
        valuePickerOptions = [
            NativeBlockValuePickerOption("match", "Match parent"),
            NativeBlockValuePickerOption("wrap", "Wrap content")
        ],
        defaultValue = "wrap"
    ) height: String = "wrap",
    @NativeBlockProp(
        description = "Specifies the weight of the layout in row or column. Default is 0.0 means not set.",
        valuePickerGroup = NativeBlockValuePickerPosition("Size"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        defaultValue = "0F"
    ) weight: Float = 0F,
    @NativeBlockProp(
        description = "The radius for the top-start corner in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        defaultValue = "0.0"
    ) radiusTopStart: Dp = 0.dp,
    @NativeBlockProp(
        description = "The radius for the top-end corner in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        defaultValue = "0.0"
    ) radiusTopEnd: Dp = 0.dp,
    @NativeBlockProp(
        description = "The radius for the bottom-start corner in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        defaultValue = "0.0"
    ) radiusBottomStart: Dp = 0.dp,
    @NativeBlockProp(
        description = "The radius for the bottom-end corner in DP.",
        valuePickerGroup = NativeBlockValuePickerPosition("Radius"),
        valuePicker = NativeBlockValuePicker.NUMBER_INPUT,
        defaultValue = "0.0"
    ) radiusBottomEnd: Dp = 0.dp,
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
        ],
        defaultValue = "none"
    ) scaleType: ContentScale = ContentScale.None,
    @NativeBlockData(
        description = "A description of the image content for accessibility purposes."
    ) contentDescription: String = "",
) {
    val shape = shapeMapper(
        "rectangle",
        radiusTopStart,
        radiusTopEnd,
        radiusBottomStart,
        radiusBottomEnd,
    )

    if (imageUrl.isHttpUrl()) {
        AsyncImage(
            model = imageUrl.trim(),
            contentDescription = contentDescription,
            contentScale = scaleType,
            modifier = Modifier
                .widthAndHeight(width, height)
                .clip(shape)
                .blockWeight(weight, blockProps?.hierarchy?.last()?.scope)
        )
    }
}
