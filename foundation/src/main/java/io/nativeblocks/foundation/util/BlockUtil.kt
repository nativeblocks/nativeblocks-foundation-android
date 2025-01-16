package io.nativeblocks.foundation.util

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import io.nativeblocks.core.api.NativeblocksResourceManager
import java.net.MalformedURLException
import java.net.URISyntaxException
import java.net.URL

/**
 * Maps a string to a vertical arrangement for layout.
 *
 * @param arrangement The string representing the arrangement type.
 * @return The corresponding Arrangement.Vertical.
 */
fun findArrangementVertical(arrangement: String?): Arrangement.Vertical {
    return if (arrangement?.toFloatOrNull() != null) {
        Arrangement.spacedBy(arrangement.toFloatOrNull()?.dp ?: 0.dp)
    } else {
        when (arrangement?.lowercase()) {
            "top" -> Arrangement.Top
            "bottom" -> Arrangement.Bottom
            "center" -> Arrangement.Center
            "spaceBetween" -> Arrangement.SpaceBetween
            "spaceAround" -> Arrangement.SpaceAround
            "spaceEvenly" -> Arrangement.SpaceEvenly
            else -> Arrangement.Top
        }
    }
}

/**
 * Maps a string to a horizontal arrangement for layout.
 *
 * @param arrangement The string representing the arrangement type.
 * @return The corresponding Arrangement.Horizontal.
 */
fun findArrangementHorizontal(arrangement: String?): Arrangement.Horizontal {
    return if (arrangement?.toFloatOrNull() != null) {
        Arrangement.spacedBy(arrangement.toFloatOrNull()?.dp ?: 0.dp)
    } else {
        when (arrangement) {
            "start" -> Arrangement.Start
            "end" -> Arrangement.End
            "center" -> Arrangement.Center
            "spaceBetween" -> Arrangement.SpaceBetween
            "spaceAround" -> Arrangement.SpaceAround
            "spaceEvenly" -> Arrangement.SpaceEvenly
            else -> Arrangement.Start
        }
    }
}

/**
 * Maps a string to an alignment for layout.
 *
 * @param alignment The string representing the alignment type.
 * @return The corresponding Alignment.
 */
fun findAlignment(alignment: String?): Alignment {
    return when (alignment) {
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

/**
 * Maps a string to a horizontal alignment for layout.
 *
 * @param alignment The string representing the alignment type.
 * @return The corresponding Alignment.Horizontal.
 */
fun findAlignmentHorizontal(alignment: String?): Alignment.Horizontal {
    return when (alignment) {
        "start" -> Alignment.Start
        "end" -> Alignment.End
        "centerHorizontally" -> Alignment.CenterHorizontally
        else -> Alignment.Start
    }
}

/**
 * Maps a string to a vertical alignment for layout.
 *
 * @param alignment The string representing the alignment type.
 * @return The corresponding Alignment.Vertical.
 */
fun findAlignmentVertical(alignment: String?): Alignment.Vertical {
    return when (alignment) {
        "top" -> Alignment.Top
        "bottom" -> Alignment.Bottom
        "centerVertically" -> Alignment.CenterVertically
        else -> Alignment.Top
    }
}

///**
// * Maps a string to a FontFamily.
// *
// * @param textFontFamily The string representing the font family name.
// * @return The corresponding FontFamily.
// */
//fun fontFamilyMapper(textFontFamily: String?): FontFamily {
//    return NativeblocksResourceManager.getFonts()[textFontFamily] ?: FontFamily.Default
//}

/**
 * Maps a string to a FontWeight.
 *
 * @param fontWeight The string representing the font weight.
 * @return The corresponding FontWeight.
 */
fun fontWeightMapper(fontWeight: String?): FontWeight {
    return when (fontWeight) {
        "thin" -> FontWeight.Thin
        "extraLight" -> FontWeight.ExtraLight
        "light" -> FontWeight.Light
        "normal" -> FontWeight.Normal
        "medium" -> FontWeight.Medium
        "semiBold" -> FontWeight.SemiBold
        "bold" -> FontWeight.Bold
        "extraBold" -> FontWeight.ExtraBold
        "black" -> FontWeight.Black
        else -> FontWeight.Normal
    }
}

/**
 * Creates a TextStyle with the specified font family, weight, and size.
 *
 * @param fontFamily The font family.
 * @param fontWeight The font weight.
 * @param fontSize The font size.
 * @return The constructed TextStyle.
 */
fun typographyBuilder(
    fontFamily: FontFamily,
    fontWeight: FontWeight,
    fontSize: TextUnit
): TextStyle {
    return TextStyle(
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        fontSize = fontSize
    )
}

/**
 * Maps a string to a TextAlign value.
 *
 * @param align The string representing the text alignment.
 * @return The corresponding TextAlign.
 */
fun textAlignmentMapper(align: String?): TextAlign {
    return when (align) {
        "start" -> TextAlign.Start
        "center" -> TextAlign.Center
        "end" -> TextAlign.End
        "justify" -> TextAlign.Justify
        else -> TextAlign.Start
    }
}

/**
 * Maps a string to a TextOverflow value.
 *
 * @param textOverflow The string representing the text overflow behavior.
 * @return The corresponding TextOverflow.
 */
fun textOverflowMapper(textOverflow: String?): TextOverflow {
    return when (textOverflow) {
        "clip" -> TextOverflow.Clip
        "ellipsis" -> TextOverflow.Ellipsis
        "visible" -> TextOverflow.Visible
        else -> TextOverflow.Clip
    }
}

/**
 * Maps a string to a TextDecoration value.
 *
 * @param textDecoration The string representing the text decoration.
 * @return The corresponding TextDecoration.
 */
fun textDecorationMapper(textDecoration: String?): TextDecoration {
    return when (textDecoration) {
        "none" -> TextDecoration.None
        "underline" -> TextDecoration.Underline
        "lineThrough" -> TextDecoration.LineThrough
        else -> TextDecoration.None
    }
}

/**
 * Maps shape properties to a Shape object.
 *
 * @param shapeName The name of the shape.
 * @param shapeRadiusTopStart The top-start corner radius.
 * @param shapeRadiusTopEnd The top-end corner radius.
 * @param shapeRadiusBottomStart The bottom-start corner radius.
 * @param shapeRadiusBottomEnd The bottom-end corner radius.
 * @return The constructed Shape.
 */
fun shapeMapper(
    shapeName: String?,
    shapeRadiusTopStart: Dp,
    shapeRadiusTopEnd: Dp,
    shapeRadiusBottomStart: Dp,
    shapeRadiusBottomEnd: Dp
): Shape {
    return when (shapeName) {
        "circle" -> CircleShape
        "rectangle" -> RoundedCornerShape(
            topStart = shapeRadiusTopStart,
            topEnd = shapeRadiusTopEnd,
            bottomStart = shapeRadiusBottomStart,
            bottomEnd = shapeRadiusBottomEnd
        )

        else -> RectangleShape
    }
}

/**
 * Maps a string to a ContentScale value.
 *
 * @param scaleType The string representing the content scale type.
 * @return The corresponding ContentScale.
 */
fun scaleTypeMapper(scaleType: String?): ContentScale {
    return when (scaleType) {
        "none" -> ContentScale.None
        "crop" -> ContentScale.Crop
        "inside" -> ContentScale.Inside
        "fit" -> ContentScale.Fit
        "fillBounds" -> ContentScale.FillBounds
        "fillWidth" -> ContentScale.FillWidth
        "fillHeight" -> ContentScale.FillHeight
        else -> ContentScale.None
    }
}

/**
 * Converts a color string and optional opacity to a Color object.
 *
 * @param colorString The string representing the color.
 * @param opacity The optional opacity value as a string.
 * @return The constructed Color.
 */
@Deprecated("Please use colorToInt()")
fun toArgb(colorString: String?, opacity: String?): Color {
    if (colorString.isNullOrEmpty()) return Color.Unspecified
    return try {
        val alpha = (opacity?.toFloatOrNull()?.div(100.0f)) ?: 1.0f
        val color = Color(android.graphics.Color.parseColor(colorString))
        color.copy(alpha = alpha)
    } catch (e: Exception) {
        Color.Unspecified
    }
}

/**
 * Applies width and height modifiers to a Modifier based on given string values.
 *
 * @param width The string representing the width.
 * @param height The string representing the height.
 * @return The modified Modifier with applied dimensions.
 */
fun Modifier.widthAndHeight(width: String?, height: String?): Modifier {
    var resultModifier = Modifier.padding(0.dp)

    resultModifier = when (width) {
        "match" -> {
            resultModifier.then(Modifier.fillMaxWidth())
        }

        "wrap" -> {
            resultModifier.then(Modifier.wrapContentWidth())
        }

        else -> {
            val w = width?.toIntOrNull() ?: 0
            resultModifier.then(Modifier.width(w.dp))
        }
    }

    resultModifier = when (height) {
        "match" -> {
            resultModifier.then(Modifier.fillMaxHeight())
        }

        "wrap" -> {
            resultModifier.then(Modifier.wrapContentHeight())
        }

        else -> {
            val h = height?.toIntOrNull() ?: 0
            resultModifier.then(Modifier.height(h.dp))
        }
    }

    return this.then(resultModifier)
}

/**
 * Maps a list of strings to PaddingValues for layout spacing.
 *
 * @param paddings The list of string values representing padding values for each side.
 * @return The constructed PaddingValues.
 */
fun spacingMapper(paddings: List<String?>): PaddingValues {
    return PaddingValues(
        start = paddings[0]?.toFloatOrNull()?.dp ?: 0.dp,
        top = paddings[1]?.toFloatOrNull()?.dp ?: 0.dp,
        end = paddings[2]?.toFloatOrNull()?.dp ?: 0.dp,
        bottom = paddings[3]?.toFloatOrNull()?.dp ?: 0.dp,
    )
}

/**
 * Checks if a string is a valid HTTP or HTTPS URL.
 *
 * @return True if the string is a valid URL, false otherwise.
 */
fun String?.isHttpUrl(): Boolean {
    if (this.isNullOrEmpty()) return false

    var tempString = this.trim()
    if (this.startsWith("http").not()) {
        tempString = "https://$tempString"
    }
    return try {
        URL(tempString).toURI()
        Patterns.WEB_URL.matcher(tempString).matches()
    } catch (e: MalformedURLException) {
        e.printStackTrace()
        false
    } catch (e: URISyntaxException) {
        e.printStackTrace()
        false
    }
}

/**
 * Maps a string to a KeyboardType value for input fields.
 *
 * @param action The string representing the keyboard type.
 * @return The corresponding KeyboardType.
 */
fun keyboardTypeMapper(action: String): KeyboardType {
    return when (action) {
        "text" -> KeyboardType.Text
        "ascii" -> KeyboardType.Ascii
        "number" -> KeyboardType.Number
        "phone" -> KeyboardType.Phone
        "uri" -> KeyboardType.Uri
        "email" -> KeyboardType.Email
        "password" -> KeyboardType.Password
        "numberPassword" -> KeyboardType.NumberPassword
        "decimal" -> KeyboardType.Decimal
        else -> KeyboardType.Text
    }
}
