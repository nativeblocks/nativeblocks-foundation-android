package io.nativeblocks.foundation.types

import androidx.compose.ui.text.input.KeyboardType
import io.nativeblocks.core.api.provider.type.INativeType

class KeyboardTypeNativeType : INativeType<KeyboardType> {
    override fun toString(input: KeyboardType?): String {
        return when (input) {
            KeyboardType.Text -> "text"
            KeyboardType.Ascii -> "ascii"
            KeyboardType.Number -> "number"
            KeyboardType.Phone -> "phone"
            KeyboardType.Uri -> "uri"
            KeyboardType.Email -> "email"
            KeyboardType.Password -> "password"
            KeyboardType.NumberPassword -> "numberPassword"
            KeyboardType.Decimal -> "decimal"
            else -> ""
        }
    }

    override fun fromString(input: String?): KeyboardType {
        return when (input?.lowercase()) {
            "text" -> KeyboardType.Text
            "ascii" -> KeyboardType.Ascii
            "number" -> KeyboardType.Number
            "phone" -> KeyboardType.Phone
            "uri" -> KeyboardType.Uri
            "email" -> KeyboardType.Email
            "password" -> KeyboardType.Password
            "numberpassword" -> KeyboardType.NumberPassword
            "decimal" -> KeyboardType.Decimal
            else -> KeyboardType.Text
        }
    }
}