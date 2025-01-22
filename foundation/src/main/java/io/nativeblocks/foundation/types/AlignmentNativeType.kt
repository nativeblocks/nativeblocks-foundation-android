package io.nativeblocks.foundation.types

import androidx.compose.ui.Alignment
import io.nativeblocks.core.api.provider.type.INativeType

class AlignmentNativeType : INativeType<Alignment> {
    override fun toString(input: Alignment?): String {
        return when (input) {
            Alignment.Center -> "center"
            Alignment.CenterStart -> "centerStart"
            Alignment.CenterEnd -> "centerEnd"
            Alignment.BottomCenter -> "bottomCenter"
            Alignment.BottomStart -> "bottomStart"
            Alignment.BottomEnd -> "bottomEnd"
            Alignment.TopStart -> "topStart"
            Alignment.TopEnd -> "topEnd"
            Alignment.TopCenter -> "topCenter"
            else -> ""
        }
    }

    override fun fromString(input: String?): Alignment {
        return when (input?.lowercase()) {
            "center" -> Alignment.Center
            "centerstart" -> Alignment.CenterStart
            "centerend" -> Alignment.CenterEnd
            "bottomcenter" -> Alignment.BottomCenter
            "bottomstart" -> Alignment.BottomStart
            "bottomend" -> Alignment.BottomEnd
            "topstart" -> Alignment.TopStart
            "topend" -> Alignment.TopEnd
            "topcenter" -> Alignment.TopCenter
            else -> Alignment.Center
        }
    }
}