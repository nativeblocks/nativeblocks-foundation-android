package io.nativeblocks.foundation.actions

import io.nativeblocks.compiler.type.NativeAction
import io.nativeblocks.compiler.type.NativeActionEvent
import io.nativeblocks.compiler.type.NativeActionFunction
import io.nativeblocks.compiler.type.NativeActionParameter
import io.nativeblocks.compiler.type.NativeActionProp
import io.nativeblocks.compiler.type.Then
import io.nativeblocks.core.api.provider.action.ActionProps
import io.nativeblocks.core.util.actionHandleVariableValue
import io.nativeblocks.core.util.replacingTypeValue

/**
 * An Action responsible for changing the properties of a block within the Nativeblocks system.
 * This action allows modification of a block's properties across different device types (Mobile, Tablet, and Desktop).
 *
 * Changes the specified property of a block.
 *
 * This function updates the mobile, tablet, and desktop values of a block's property.
 * It supports variable substitution and conditional evaluation for the property values.
 *
 * Property Value Supported Formats:
 *   - `{var:variable-key}`: Replaces with the value of the variable.
 *   - `{index}`: Replaces with the list item index.
 *   - `#SCRIPT 2 + 2 #ENDSCRIPT`: The string with evaluated JavaScript code replacing the script tags.
 */
@NativeAction(
    keyType = "NATIVE_CHANGE_BLOCK_PROPERTY",
    name = "Native Change Block Property",
    description = "Native Change Block Property",
    version = 1
)
class NativeChangeBlockProperty {
    /**
     * @param actionProps The properties of the action, including blocks and variables.
     * @param blockKey The unique key of the block whose property is being changed.
     * @param propertyKey The key of the specific property to be changed.
     * @param propertyValueMobile The new value for the block's mobile property.
     * @param propertyValueTablet The new value for the block's tablet property.
     * @param propertyValueDesktop The new value for the block's desktop property.
     * @param onNext A callback function to be invoked once the property update is completed.
     */
    @NativeActionParameter
    data class Parameter(
        val actionProps: ActionProps,
        @NativeActionProp(description = "key of the block")
        val blockKey: String,
        @NativeActionProp(description = "key of the block's property")
        val propertyKey: String,
        @NativeActionProp(description = "new value for the block's Mobile property")
        val propertyValueMobile: String,
        @NativeActionProp(description = "new value for the block's Tablet property")
        val propertyValueTablet: String,
        @NativeActionProp(description = "new value for the block's Desktop property")
        val propertyValueDesktop: String,
        @NativeActionEvent(then = Then.NEXT)
        val onNext: () -> Unit
    )

    @NativeActionFunction
    fun invoke(param: Parameter) {
        var valueMobile = param.propertyValueMobile
        var valueTablet = param.propertyValueTablet
        var valueDesktop = param.propertyValueDesktop

        val block = param.actionProps.blocks?.get(param.blockKey)
        val blockProperties = block?.properties.orEmpty().toMutableMap()
        var currentProperty = blockProperties[param.propertyKey]
        if (currentProperty != null) {
            if (param.propertyValueMobile.isNotEmpty()) {
                valueMobile = actionHandleVariableValue(param.actionProps, valueMobile) ?: ""
                valueMobile = valueMobile.replacingTypeValue(type = currentProperty.type)
                currentProperty = currentProperty.copy(valueMobile = valueMobile)
            }
            if (param.propertyValueTablet.isNotEmpty()) {
                valueTablet = actionHandleVariableValue(param.actionProps, valueTablet) ?: ""
                valueTablet = valueTablet.replacingTypeValue(type = currentProperty.type)
                currentProperty = currentProperty.copy(valueTablet = valueTablet)
            }
            if (param.propertyValueDesktop.isNotEmpty()) {
                valueDesktop = actionHandleVariableValue(param.actionProps, valueDesktop) ?: ""
                valueDesktop = valueDesktop.replacingTypeValue(type = currentProperty.type)
                currentProperty = currentProperty.copy(valueDesktop = valueDesktop)
            }
            blockProperties[currentProperty.key] = currentProperty
        }
        if (block != null) {
            param.actionProps.onChangeBlock?.invoke(block.copy(properties = blockProperties))
        }
        param.onNext()
    }
}
