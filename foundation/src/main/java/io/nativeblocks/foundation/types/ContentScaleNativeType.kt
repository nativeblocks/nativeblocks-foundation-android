package io.nativeblocks.foundation.types

import androidx.compose.ui.layout.ContentScale
import io.nativeblocks.core.api.provider.type.INativeType

class ContentScaleNativeType : INativeType<ContentScale> {
    override fun toString(input: ContentScale?): String {
        return when (input) {
            ContentScale.None -> "none"
            ContentScale.Crop -> "crop"
            ContentScale.Inside -> "inside"
            ContentScale.Fit -> "fit"
            ContentScale.FillBounds -> "fillBounds"
            ContentScale.FillWidth -> "fillWidth"
            ContentScale.FillHeight -> "fillHeight"
            else -> ""
        }
    }

    override fun fromString(input: String?): ContentScale {
        return when (input?.lowercase()) {
            "none" -> ContentScale.None
            "crop" -> ContentScale.Crop
            "inside" -> ContentScale.Inside
            "fit" -> ContentScale.Fit
            "fillbounds" -> ContentScale.FillBounds
            "fillwidth" -> ContentScale.FillWidth
            "fillheight" -> ContentScale.FillHeight
            else -> ContentScale.None
        }
    }
}