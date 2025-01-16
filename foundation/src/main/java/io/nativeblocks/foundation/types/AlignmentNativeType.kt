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
            else -> "center"
        }
    }

    override fun fromString(input: String?): Alignment {
        return when (input) {
            "center" -> Alignment.Center
            "centerStart" -> Alignment.CenterStart
            "centerEnd" -> Alignment.CenterEnd
            "bottomCenter" -> Alignment.BottomCenter
            "bottomStart" -> Alignment.BottomStart
            "bottomEnd" -> Alignment.BottomEnd
            "topStart" -> Alignment.TopStart
            "topEnd" -> Alignment.TopEnd
            "topCenter" -> Alignment.TopCenter
            else -> Alignment.Center
        }
    }
}