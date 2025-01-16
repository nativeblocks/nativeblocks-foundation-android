package io.nativeblocks.foundation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.TextUnit
import io.nativeblocks.core.api.NativeblocksManager
import io.nativeblocks.foundation.types.AlignmentNativeType
import io.nativeblocks.foundation.types.ColorNativeType
import io.nativeblocks.foundation.types.ContentScaleNativeType
import io.nativeblocks.foundation.types.DpNativeType
import io.nativeblocks.foundation.types.FontWeightNativeType
import io.nativeblocks.foundation.types.HorizontalAlignmentNativeType
import io.nativeblocks.foundation.types.HorizontalArrangementNativeType
import io.nativeblocks.foundation.types.LayoutDirectionNativeType
import io.nativeblocks.foundation.types.TextAlignNativeType
import io.nativeblocks.foundation.types.TextUnitNativeType
import io.nativeblocks.foundation.types.VerticalArrangementNativeType

object FoundationTypeProvider {
    fun provideTypes() {
        NativeblocksManager.getInstance().provideTypeConverter(Dp::class, DpNativeType())
        NativeblocksManager.getInstance().provideTypeConverter(Color::class, ColorNativeType())
        NativeblocksManager.getInstance().provideTypeConverter(LayoutDirection::class, LayoutDirectionNativeType())
        NativeblocksManager.getInstance().provideTypeConverter(TextUnit::class, TextUnitNativeType())
        NativeblocksManager.getInstance().provideTypeConverter(Alignment::class, AlignmentNativeType())
        NativeblocksManager.getInstance().provideTypeConverter(FontWeight::class, FontWeightNativeType())
        NativeblocksManager.getInstance().provideTypeConverter(TextAlign::class, TextAlignNativeType())
        NativeblocksManager.getInstance().provideTypeConverter(ContentScale::class, ContentScaleNativeType())
        NativeblocksManager.getInstance().provideTypeConverter(Arrangement.Horizontal::class, HorizontalArrangementNativeType())
        NativeblocksManager.getInstance().provideTypeConverter(Alignment.Horizontal::class, HorizontalAlignmentNativeType())
        NativeblocksManager.getInstance().provideTypeConverter(Arrangement.Vertical::class, VerticalArrangementNativeType())
    }
}