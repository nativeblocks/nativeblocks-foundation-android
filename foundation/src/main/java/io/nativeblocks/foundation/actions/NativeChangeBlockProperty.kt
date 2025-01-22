package io.nativeblocks.foundation.actions

import io.nativeblocks.compiler.type.NativeAction
import io.nativeblocks.compiler.type.NativeActionEvent
import io.nativeblocks.compiler.type.NativeActionFunction
import io.nativeblocks.compiler.type.NativeActionParameter
import io.nativeblocks.compiler.type.NativeActionProp
import io.nativeblocks.compiler.type.Then
import io.nativeblocks.core.api.provider.action.ActionProps
import io.nativeblocks.core.util.evaluateMixConditionOperator
import io.nativeblocks.core.util.getVariableValue

/**
 * Evaluates a propertyValue that may contain mixed conditions and operators based on a property type
 * specified by propertyKey. If the string contains conditions, it evaluates them and converts the result
 * to a boolean string if the property type is "BOOLEAN". If the string contains operators, it evaluates
 * the expression and converts the result to the specified numeric type.
 *
 * Supported types: "BOOLEAN", "INT", "DOUBLE", "LONG", "FLOAT".
 *
 * The evaluated propertyValue is returned as a string, or the original value if no conditions or operators are found.
 *
 * A class responsible for changing the properties of a block within the Nativeblocks system.
 * This action allows modification of a block's properties across different device types (Mobile, Tablet, and Desktop).
 *
 * Example 1 (for BOOLEAN type):
 * - propertyValue: "(4 / 2 != 0) && (true == true)"
 * - Evaluated value: "true" (evaluates the condition and returns boolean as string)
 *
 * Example 2 (for INT type):
 * - propertyValue: "(3+1)/2"
 * - Evaluated value: "2" (evaluates the arithmetic expression and returns the result as an integer)
 *
 * Example 3 (for STRING type):
 * - propertyValue: "\"test\" == \"test\""
 * - Evaluated value: "true" (evaluates string equality and returns the result as string)
 *
 * Example 4 (for FLOAT type):
 * - propertyValue: "2 * 2.5"
 * - Evaluated value: "5.0" (evaluates multiplication and returns the result as float)
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
                param.actionProps.variables?.forEach { variableItem ->
                    valueMobile =
                        valueMobile.getVariableValue(variableItem.key, variableItem.value.value)
                }
                valueMobile = valueMobile.evaluateMixConditionOperator(type = currentProperty.type)
                currentProperty = currentProperty.copy(valueMobile = valueMobile)
            }
            if (param.propertyValueTablet.isNotEmpty()) {
                param.actionProps.variables?.forEach { variableItem ->
                    valueTablet =
                        valueTablet.getVariableValue(variableItem.key, variableItem.value.value)
                }
                valueTablet = valueTablet.evaluateMixConditionOperator(type = currentProperty.type)
                currentProperty = currentProperty.copy(valueTablet = valueTablet)
            }
            if (param.propertyValueDesktop.isNotEmpty()) {
                param.actionProps.variables?.forEach { variableItem ->
                    valueDesktop =
                        valueDesktop.getVariableValue(variableItem.key, variableItem.value.value)
                }
                valueDesktop =
                    valueDesktop.evaluateMixConditionOperator(type = currentProperty.type)
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
