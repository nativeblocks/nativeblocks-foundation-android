package io.nativeblocks.foundation.util

import android.util.Patterns
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.net.MalformedURLException
import java.net.URISyntaxException
import java.net.URL

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

fun Modifier.blockWeight(weight: Float, scope: Any?): Modifier {
    var modifier = this
    if (weight > 0f) {
        when (scope) {
            is RowScope -> {
                scope.apply {
                    modifier = modifier.weight(weight)
                }
            }

            is ColumnScope -> {
                scope.apply {
                    modifier = modifier.weight(weight)
                }
            }
        }
    }
    return modifier
}